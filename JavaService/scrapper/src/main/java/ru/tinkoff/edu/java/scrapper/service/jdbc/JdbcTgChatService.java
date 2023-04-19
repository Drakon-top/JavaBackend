package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

@Component
@RequiredArgsConstructor
public class JdbcTgChatService implements TgChatService {

    private final JdbcRequestClientRepository jdbcRequestUserLinks;

    @Override
    public void register(long tgChatId, String userName) {
        jdbcRequestUserLinks.addUser(tgChatId, userName);
    }

    @Override
    public void unregister(long tgChatId) {
        jdbcRequestUserLinks.removeUser(tgChatId);
    }
}
