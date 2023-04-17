package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Client;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.ClientRecord;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

@RequiredArgsConstructor
public class JoopTgChetService implements TgChatService {

    private final DSLContext dslContext;

    @Override
    public void register(long tgChatId, String userName) {
        dslContext.insertInto(Client.CLIENT)
                .set(new ClientRecord(tgChatId, userName));
    }

    @Override
    public void unregister(long tgChatId) {
        dslContext.delete(Client.CLIENT).where(Client.CLIENT.CHAT_ID.eq(tgChatId));
    }
}
