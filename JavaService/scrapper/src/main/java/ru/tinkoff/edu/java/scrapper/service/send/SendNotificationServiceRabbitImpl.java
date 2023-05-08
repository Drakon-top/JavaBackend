package ru.tinkoff.edu.java.scrapper.service.send;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.service.rabbitmq.ScrapperQueueProducer;

@RequiredArgsConstructor
public class SendNotificationServiceRabbitImpl implements SendNotificationService {

    private final ScrapperQueueProducer scrapperQueueProducer;

    @Override
    public void sendRequest(LinkUpdateRequest request) {
        scrapperQueueProducer.send(request);
    }

}
