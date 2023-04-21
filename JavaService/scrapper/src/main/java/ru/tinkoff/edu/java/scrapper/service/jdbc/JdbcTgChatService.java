package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.dto.UserInfoResponse;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserWithInfo;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

//@Service
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

    @Override
    public void updateStateUser(long tgChatId, String userState) {
        jdbcRequestUserLinks.updateStateUser(tgChatId, userState);
    }

    @Override
    public DataUserWithInfo getUser(long tgChatId) {
        return jdbcRequestUserLinks.getUser(tgChatId);
    }

}
