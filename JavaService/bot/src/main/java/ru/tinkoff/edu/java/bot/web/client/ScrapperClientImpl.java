package ru.tinkoff.edu.java.bot.web.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkResponse;
import ru.tinkoff.edu.java.bot.web.dto.DeleteLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.DeleteLinkResponse;

public class ScrapperClientImpl implements ScrapperClient {
    private final String BASE_URL = "http://localhost:8077";
    private final WebClient webClient;

    public ScrapperClientImpl() {
        webClient = WebClient.create(BASE_URL);
    }

    public ScrapperClientImpl(String url) {
        webClient = WebClient.create(url);
    }

    @Override
    public Mono<AddLinkResponse> addTrackedLink(AddLinkRequest request) {
        return webClient.post().uri(uriBuilder -> uriBuilder.path("/links").queryParam("Tg-Chat-Id", request.id()).build())
                .bodyValue(request.url())
                .retrieve()
                .bodyToMono(AddLinkResponse.class);
    }

    @Override
    public Mono<DeleteLinkResponse> deleteTrackedLink(DeleteLinkRequest request) {
        return webClient.method(HttpMethod.DELETE).uri(uriBuilder -> uriBuilder.path("/links").queryParam("Tg-Chat-Id", request.id()).build())
                .bodyValue(request.url())
                .retrieve()
                .bodyToMono(DeleteLinkResponse.class);
    }


}
