package ru.tinkoff.edu.java.scrapper.service.send;

import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;

public interface SendNotificationService {

    void sendRequest(LinkUpdateRequest request);
}
