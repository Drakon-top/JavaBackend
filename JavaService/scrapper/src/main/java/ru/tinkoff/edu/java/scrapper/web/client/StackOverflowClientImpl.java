package ru.tinkoff.edu.java.scrapper.web.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowQuestionResponse;

public class StackOverflowClientImpl implements StackOverflowClient {
    private final String BASE_URL = "https://api.stackexchange.com";
    private final WebClient webClient;

    public StackOverflowClientImpl() {
        webClient = WebClient.create(BASE_URL);
    }

    public StackOverflowClientImpl(String url) {
        webClient = WebClient.create(url);
    }
    @Override
    public Mono<StackOverflowQuestionResponse> fetchInfoQuestion(int numberQuestion) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/questions/{number}").build(numberQuestion))
                .retrieve().bodyToMono(StackOverflowQuestionResponse.class);
    }

}
