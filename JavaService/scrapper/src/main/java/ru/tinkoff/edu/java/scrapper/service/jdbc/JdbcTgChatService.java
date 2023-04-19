package ru.tinkoff.edu.java.scrapper.service.jdbc;

import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestClientTable;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

public class JdbcTgChatService implements TgChatService {

    private JdbcRequestClientTable jdbcRequestUserLinks;

    public JdbcTgChatService(JdbcRequestClientTable jdbcRequestUserLinks) {
        this.jdbcRequestUserLinks = jdbcRequestUserLinks;
    }
    @Override
    public void register(long tgChatId, String userName) {
        jdbcRequestUserLinks.addUser(tgChatId, userName);
    }

    @Override
    public void unregister(long tgChatId) {
        jdbcRequestUserLinks.removeUser(tgChatId);
    }
}
