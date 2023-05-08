package ru.tinkoff.edu.java.scrapper.service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;

@Service
public class ScrapperQueueProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routeKey;

    public ScrapperQueueProducer(RabbitTemplate rabbitTemplate, ApplicationConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        exchange = config.exchange();
        routeKey = config.queue();
    }

    public void send(LinkUpdateRequest update) {
        rabbitTemplate.convertAndSend(exchange, routeKey, update);
    }
}