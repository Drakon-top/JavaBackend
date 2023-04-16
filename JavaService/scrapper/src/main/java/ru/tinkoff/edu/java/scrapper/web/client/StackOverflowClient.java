package ru.tinkoff.edu.java.scrapper.web.client;

import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowQuestionResponse;

public interface StackOverflowClient {
    Mono<StackOverflowQuestionResponse> fetchInfoQuestion(Long numberQuestion);
}

