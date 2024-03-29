package ru.tinkoff.edu.java.bot.web.client;

import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.AddLinkResponse;
import ru.tinkoff.edu.java.bot.dto.DeleteLinkRequest;
import ru.tinkoff.edu.java.bot.dto.DeleteLinkResponse;
import ru.tinkoff.edu.java.bot.dto.ListLinkRequest;
import ru.tinkoff.edu.java.bot.dto.ListLinkResponse;
import ru.tinkoff.edu.java.bot.dto.UserGetResponse;
import ru.tinkoff.edu.java.bot.enums.StateUser;

public interface ScrapperClient {
    Mono<AddLinkResponse> addTrackedLink(AddLinkRequest request);

    Mono<DeleteLinkResponse> deleteTrackedLink(DeleteLinkRequest request);

    Mono<ListLinkResponse> listTrackedLink(ListLinkRequest request);

    void registerChat(Long id, String userName);

    void deleteChat(Long id);

    void updateUser(Long id, StateUser stateUser);

    Mono<UserGetResponse> getUser(Long id);
}
