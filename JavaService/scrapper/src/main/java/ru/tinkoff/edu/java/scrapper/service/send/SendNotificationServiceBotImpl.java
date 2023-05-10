package ru.tinkoff.edu.java.scrapper.service.send;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.web.client.BotClient;

@RequiredArgsConstructor
public class SendNotificationServiceBotImpl implements SendNotificationService {

    private final BotClient botClient;

    @Override
    public void sendRequest(LinkUpdateRequest request) {
        botClient.updater(request);
    }

}
