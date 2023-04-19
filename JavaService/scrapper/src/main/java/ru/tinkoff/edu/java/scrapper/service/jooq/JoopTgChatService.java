package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ru.tinkoff.edu.java.scrapper.domain.jooq.JooqRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Client;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.ClientRecord;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

@RequiredArgsConstructor
public class JoopTgChatService implements TgChatService {

    private final JooqRequestClientRepository jooqRequestClientRepository;

    @Override
    public void register(long tgChatId, String userName) {
        jooqRequestClientRepository.addUser(tgChatId, userName);
    }

    @Override
    public void unregister(long tgChatId) {
        jooqRequestClientRepository.removeUser(tgChatId);
    }
}
