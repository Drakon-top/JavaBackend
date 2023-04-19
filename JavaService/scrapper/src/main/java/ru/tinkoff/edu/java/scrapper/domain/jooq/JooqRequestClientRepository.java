package ru.tinkoff.edu.java.scrapper.domain.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Client;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.UserLinks;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.ClientRecord;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUser;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;

import java.util.List;

import static org.jooq.impl.DSL.count;

@Component
@RequiredArgsConstructor
public class JooqRequestClientRepository {
    private final DSLContext dslContext;

    @Transactional
    public void addUser(Long chatId, String userName) {
        if (!userAlreadyRegister(chatId)) {
            dslContext.insertInto(Client.CLIENT, Client.CLIENT.CHAT_ID, Client.CLIENT.USER_NAME)
                    .values(chatId, userName).execute();
        }
    }

    @Transactional
    public void removeUser(Long chatId) {
        dslContext.delete(Client.CLIENT).where(Client.CLIENT.CHAT_ID.eq(chatId)).execute();
    }

    public boolean userAlreadyRegister(Long chatId) {
        Long count = dslContext.select(count(Client.CLIENT.CHAT_ID))
                .from(Client.CLIENT)
                .where(Client.CLIENT.CHAT_ID.eq(chatId))
                .fetchOne(0, Long.class);
        return count > 0;
    }

    public List<DataUser> findAllUsers() {
        List<DataUser> dataUserLinks = dslContext.select(Client.CLIENT.CHAT_ID, Client.CLIENT.USER_NAME)
                .from(Client.CLIENT).fetch().into(DataUser.class);
        return dataUserLinks;
    }
}
