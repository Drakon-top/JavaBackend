package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ru.tinkoff.app.ParsingUrlService;
import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.app.url.UrlDataGitHub;
import ru.tinkoff.app.url.UrlDataStackOverflow;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestLinkTable;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestUserLinksTable;
import ru.tinkoff.edu.java.scrapper.dto.GitHubCommitsResponse;
import ru.tinkoff.edu.java.scrapper.dto.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowAnswersResponse;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowQuestionResponse;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;
import ru.tinkoff.edu.java.scrapper.web.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.client.StackOverflowClient;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private static final int COUNT_LINK_LIMIT = 10;
    private final JdbcRequestLinkTable jdbcRequestLink;
    private final JdbcRequestUserLinksTable jdbcRequestUserLinks;

    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;


    @Override
    public DataLink add(long tgChatId, URI url) {
        UrlData urlData = ParsingUrlService.getInfoAboutURL(url.toString());
        if (urlData == null) {
            return null;
        }
        OffsetDateTime timeEditLast = timeEditLinkForType(urlData);
        Integer count = getCountAnswer(urlData);
        DataLink dataLink = jdbcRequestLink.addLink(url, timeEditLast, count);
        jdbcRequestUserLinks.addUserLink(tgChatId, dataLink.getId());
        return dataLink;
    }

    @Override
    public DataLink remove(long tgChatId, URI url) {
        DataLink dataLink = jdbcRequestLink.removeLink(url);
        jdbcRequestUserLinks.removeLink(tgChatId, dataLink.getId());
        List<DataUserLinks> dataUserLinksList = jdbcRequestUserLinks.findUserLinksByLink(dataLink.getId());
        if (dataUserLinksList.size() == 0) {
            jdbcRequestLink.removeLink(url);
        }
        return dataLink;
    }

    @Override
    public Collection<DataLink> listAll(long tgChatId) {
        List<DataUserLinks> dataUserLinksList = jdbcRequestUserLinks.findUserLinksByUser(tgChatId);
        Set<Long> setLinksId = dataUserLinksList.stream().map(DataUserLinks::getLinksId).collect(Collectors.toSet());
        List<DataLink> dataLinks = jdbcRequestLink.findAllLinks();
        return dataLinks.stream().filter(x -> setLinksId.contains(x.getId())).toList();
    }

    @Override
    public List<DataLinkWithInformation> listLongTimeUpdate() {
        return jdbcRequestLink.findLinkNotUpdateLongTime(COUNT_LINK_LIMIT);
    }

    @Override
    public List<DataUserLinks> findUserLinksByLinks(long idLink) {
        return jdbcRequestUserLinks.findUserLinksByLink(idLink);
    }


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
        return Objects.requireNonNull(response.block()).answers().length;
    }
}
