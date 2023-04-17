package ru.tinkoff.edu.java.scrapper.web.sheduler;

import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ru.tinkoff.app.ParsingUrlService;
import ru.tinkoff.app.enums.TypeClient;
import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.app.url.UrlDataGitHub;
import ru.tinkoff.app.url.UrlDataStackOverflow;
import ru.tinkoff.edu.java.scrapper.dto.*;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.web.client.BotClient;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClientImpl;
import ru.tinkoff.edu.java.scrapper.web.client.StackOverflowClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {
    private static final Logger log =
            LoggerFactory.getLogger(LinkUpdaterScheduler.class);

    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final LinkService jdbcLinkService;

    private final BotClient botClient;


    @Scheduled(fixedDelayString = "#{schedulerIntervalMs}")
    public void update() {
        List<DataLinkWithInformation> dataLinkWithInformation = jdbcLinkService.listLongTimeUpdate();
        for (DataLinkWithInformation data : dataLinkWithInformation) {
            UrlData urlData = ParsingUrlService.getInfoAboutURL(data.getUrl().toString());
            if (urlData == null) {
                continue;
            }
            OffsetDateTime timeEdit = timeEditLinkForType(urlData);
            if (!timeEdit.equals(data.getLastEditTime())) {
                Integer countAnswerNow = getCountAnswer(urlData);
                String description = "";
                if (countAnswerNow > data.getCountAnswer()) {
                    description = getInfoCountByType(urlData.getType());
                }
                List<DataUserLinks> dataUserLinks = jdbcLinkService.findUserLinksByLinks(data.getId());
                botClient.updater(new LinkUpdateRequest(data.getId(), data.getUrl(), description,
                        dataUserLinks.stream().map(DataUserLinks::getUserId).toList()));
            }
        }
        log.info("Update info about urls");
    }


    /// Лютейшая копипаста в LinkService есть тоже самое, не пойму куда по логике это вынести, или просто расширить интерфейс у LinkService?
    private OffsetDateTime timeEditLinkForType(UrlData urlData) {
        return switch (urlData.getType()) {
            case GITHUB -> workWithGitHubClient((UrlDataGitHub) urlData);
            case STACKOVERFLOW -> workWithStackOverflowClient((UrlDataStackOverflow) urlData);
            default -> null;
        };
    }

    private OffsetDateTime workWithGitHubClient(UrlDataGitHub urlData) {
        Mono<GitHubRepositoryResponse> response = gitHubClient.fetchInfoRepository(urlData.userName(), urlData.repository());
        try {
            GitHubRepositoryResponse result = response.block();
            return result.timeLastUpdate();
        } catch (WebClientResponseException | NullPointerException e) {
            return OffsetDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.of(1, 1, 1), ZoneOffset.ofHours(3));
        }

    }

    private OffsetDateTime workWithStackOverflowClient(UrlDataStackOverflow urlData) {
        StackOverflowQuestionResponse response = stackOverflowClient.fetchInfoQuestion(urlData.idQuestion());
        return response.lastEditDate();
    }

    private Integer getCountAnswer(UrlData urlData) {
        return switch (urlData.getType()) {
            case GITHUB -> getCountAnswerGitHub((UrlDataGitHub) urlData);
            case STACKOVERFLOW -> getCountAnswerStackOverflow((UrlDataStackOverflow) urlData);
            default -> null;
        };
    }

    private Integer getCountAnswerGitHub(UrlDataGitHub urlData) {
        GitHubCommitsResponse response = gitHubClient.fetchCommitsRepository(urlData.userName(), urlData.repository());
        if (response == null) {
            return 0;
        }
        return response.commitList().size();
    }

    private Integer getCountAnswerStackOverflow(UrlDataStackOverflow urlData) {
        Mono<StackOverflowAnswersResponse> response = stackOverflowClient.fetchAnswersRepository(urlData.idQuestion());
        Integer res = Objects.requireNonNull(response.block()).answers().length;
        return res;
    }

    private String getInfoCountByType(TypeClient type) {
        return switch (type) {
            case GITHUB -> "New commit";
            case STACKOVERFLOW -> "New answer";
            default -> "";
        };
    }
}
