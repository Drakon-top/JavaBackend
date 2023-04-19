package ru.tinkoff.edu.java.scrapper.domain.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.UserLinks;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.UserLinksRecord;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JooqRequestUserLinksRepository {
    private final DSLContext dslContext;

    @Transactional
    public void addUserLink(long chatId, long linkId) {
        dslContext.insertInto(UserLinks.USER_LINKS)
                .set(new UserLinksRecord(chatId, linkId)).execute();
    }

    @Transactional
    public void removeLink(long chatId, long linkId) {
        dslContext.delete(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.LINKS_ID.eq(linkId), UserLinks.USER_LINKS.USER_ID.eq(chatId)).execute();
    }

    public List<DataUserLinks> findAllUserLinks() {
        List<DataUserLinks> dataUserLinks = dslContext.select(UserLinks.USER_LINKS)
                .from(UserLinks.USER_LINKS)
                .fetch().into(DataUserLinks.class);
        return dataUserLinks;
    }

    public List<DataUserLinks> findUserLinksByUser(long chat_id) {
        List<DataUserLinks> dataUserLinks = dslContext.select(UserLinks.USER_LINKS.USER_ID, UserLinks.USER_LINKS.LINKS_ID)
                .from(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.USER_ID.eq(chat_id))
                .fetch().into(DataUserLinks.class);
        return dataUserLinks;
    }

    public List<DataUserLinks> findUserLinksByLink(long link_id) {
        List<DataUserLinks> dataUserLinks = dslContext.select(UserLinks.USER_LINKS.USER_ID, UserLinks.USER_LINKS.LINKS_ID)
                .from(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.LINKS_ID.eq(link_id))
                .fetch().into(DataUserLinks.class);
        return dataUserLinks;
    }
}
