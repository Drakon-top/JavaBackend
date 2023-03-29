package ru.tinkoff.edu.java.scrapper.web.client;

import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.web.dto.GitHubRepositoryResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;

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
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/links").queryParam("").build(userName, repo))
                .retrieve().bodyToMono(GitHubRepositoryResponse.class);
    }
}
