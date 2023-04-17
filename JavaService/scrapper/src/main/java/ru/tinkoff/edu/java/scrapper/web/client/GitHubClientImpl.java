package ru.tinkoff.edu.java.scrapper.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.GitHubCommitsResponse;
import ru.tinkoff.edu.java.scrapper.dto.GitHubRepositoryResponse;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.GitHunCommit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GitHubClientImpl implements GitHubClient {

    private final String BASE_URL = "https://api.github.com";
    private final WebClient webClient;

    public GitHubClientImpl() {
        webClient = WebClient.create(BASE_URL);
    }

    public GitHubClientImpl(String url) {
        webClient = WebClient.create(url);
    }

    @Override
    public Mono<GitHubRepositoryResponse> fetchInfoRepository(String userName, String repo) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/repos/{user_name}/{repos}").build(userName, repo))
                .retrieve().bodyToMono(GitHubRepositoryResponse.class);
    }

    @Override
    public GitHubCommitsResponse fetchCommitsRepository(String userName, String repo) {
        Mono<GitHunCommit[]> response = webClient.get().uri(uriBuilder -> uriBuilder.path("/repos/{user_name}/{repos}/commits").build(userName, repo))
                .retrieve().bodyToMono(GitHunCommit[].class);
        try {
            GitHunCommit[] commitsResponses = response.block();
            GitHubCommitsResponse gitHubCommitsResponse = new GitHubCommitsResponse(List.of(commitsResponses));
            return gitHubCommitsResponse;
        } catch (WebClientResponseException | NullPointerException e) {
            return new GitHubCommitsResponse(List.of());
        }
    }
}
