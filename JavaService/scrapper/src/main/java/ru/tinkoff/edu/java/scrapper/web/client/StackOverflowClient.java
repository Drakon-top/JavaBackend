package ru.tinkoff.edu.java.scrapper.web.client;

import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.web.dto.StackOverflowQuestionResponse;

public interface StackOverflowClient {
    Mono<StackOverflowQuestionResponse> fetchInfoQuestion(int numberQuestion);
}

