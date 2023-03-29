package ru.tinkoff.edu.java.bot.web.client;

import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkResponse;
import ru.tinkoff.edu.java.bot.web.dto.DeleteLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.DeleteLinkResponse;

public interface ScrapperClient {
    Mono<AddLinkResponse> addTrackedLink(AddLinkRequest request);

    Mono<DeleteLinkResponse> deleteTrackedLink(DeleteLinkRequest request);
}
