package ru.tinkoff.edu.java.scrapper.web.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.web.dto.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.web.dto.StackOverflowQuestionResponse;

public class StackOverflowClientImpl implements StackOverflowClient {
    private final String BASE_URL = "https://api.stackexchange.com";

    @Override
    public Mono<StackOverflowQuestionResponse> fetchInfoQuestion(int numberQuestion) {
        WebClient webClient = WebClient.create(BASE_URL);
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/questions/{number}").build(numberQuestion))
                .retrieve().bodyToMono(StackOverflowQuestionResponse.class);
    }

}
