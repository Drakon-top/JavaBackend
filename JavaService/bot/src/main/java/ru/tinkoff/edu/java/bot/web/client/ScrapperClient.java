package ru.tinkoff.edu.java.bot.web.client;

import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.web.dto.*;

public interface ScrapperClient {
    Mono<AddLinkResponse> addTrackedLink(AddLinkRequest request);

    Mono<DeleteLinkResponse> deleteTrackedLink(DeleteLinkRequest request);

    Mono<ListLinkResponse> listTrackedLink(ListLinkRequest request);
}
