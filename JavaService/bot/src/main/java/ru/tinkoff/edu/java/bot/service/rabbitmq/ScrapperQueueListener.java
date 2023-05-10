package ru.tinkoff.edu.java.bot.service.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.Bot;

@Component
@RabbitListener(queues = "${app.scrapper-queue-name}")
@RequiredArgsConstructor
public class ScrapperQueueListener {
    private final Bot tgBot;

    @RabbitHandler
    public void receiver(LinkUpdateRequest update) {
        tgBot.mailingToUsers(update);
    }
}
