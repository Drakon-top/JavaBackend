package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.service.send.SendNotificationService;
import ru.tinkoff.edu.java.scrapper.service.send.SendNotificationServiceBotImpl;
import ru.tinkoff.edu.java.scrapper.web.client.BotClient;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "useRabbitMQ", havingValue = "false")
public class SendNotificationBot {
    @Bean
    public SendNotificationService getSendNotificationBot(BotClient botClient) {
        return new SendNotificationServiceBotImpl(botClient);
    }
}
