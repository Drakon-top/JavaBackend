package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestUserLinksRepository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.JpaRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.domain.jpa.JpaRequestLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcTgChatService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaTgChatService;
import ru.tinkoff.edu.java.scrapper.web.ClientManager;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
    @Bean
    public LinkService getLinkService(
            ClientManager clientManager,
            JdbcRequestLinkRepository jdbcRequestLinkRepository,
            JdbcRequestUserLinksRepository jdbcRequestUserLinksRepository) {
        return new JdbcLinkService(jdbcRequestLinkRepository, jdbcRequestUserLinksRepository, clientManager);
    }

    @Bean
    public TgChatService getTgChetService(
            JdbcRequestClientRepository jdbcRequestClientRepository) {
        return new JdbcTgChatService(jdbcRequestClientRepository);
    }
}