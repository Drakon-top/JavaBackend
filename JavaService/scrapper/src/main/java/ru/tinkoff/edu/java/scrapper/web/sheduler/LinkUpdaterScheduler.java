package ru.tinkoff.edu.java.scrapper.web.sheduler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.tinkoff.app.ParsingUrlService;
import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.app.url.UrlDataGitHub;
import ru.tinkoff.app.url.UrlDataStackOverflow;
import ru.tinkoff.edu.java.scrapper.dto.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowQuestionResponse;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.web.client.BotClient;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClientImpl;
import ru.tinkoff.edu.java.scrapper.web.client.StackOverflowClient;

import java.time.OffsetDateTime;
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
        log.info("Update info about urls");
        List<DataLinkWithInformation> dataLinkWithInformation = jdbcLinkService.listLongTimeUpdate();
        for (DataLinkWithInformation data : dataLinkWithInformation) {
            UrlData urlData = ParsingUrlService.getInfoAboutURL(data.getUrl().toString());
            if (urlData == null) {
                continue;
            }
            OffsetDateTime timeEdit = timeEditLinkForType(urlData);
            if (!timeEdit.equals(data.getLastEditTime())) {
                List<DataUserLinks> dataUserLinks = jdbcLinkService.findUserLinksByLinks(data.getId());
                botClient.updater(new LinkUpdateRequest(data.getId(), data.getUrl(), "description",
                        dataUserLinks.stream().map(DataUserLinks::getUserId).toList()));
            }
        }
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
        return Objects.requireNonNull(response.block()).timeLastUpdate();
    }

    private OffsetDateTime workWithStackOverflowClient(UrlDataStackOverflow urlData) {
        Mono<StackOverflowQuestionResponse> response = stackOverflowClient.fetchInfoQuestion(urlData.idQuestion());
        return Objects.requireNonNull(response.block()).lastEditDate();
    }
}
