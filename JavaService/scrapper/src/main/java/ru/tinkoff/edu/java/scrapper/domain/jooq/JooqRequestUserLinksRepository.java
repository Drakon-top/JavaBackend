package ru.tinkoff.edu.java.scrapper.domain.jooq;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.UserLinks;
import ru.tinkoff.edu.java.scrapper.domain.jooq.codegen.tables.records.UserLinksRecord;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;

@RequiredArgsConstructor
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

    public List<DataUserLinks> findUserLinksByUser(long chatId) {
        List<DataUserLinks> dataUserLinks =
            dslContext.select(UserLinks.USER_LINKS.USER_ID, UserLinks.USER_LINKS.LINKS_ID)
                .from(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.USER_ID.eq(chatId))
                .fetch().into(DataUserLinks.class);
        return dataUserLinks;
    }

    public List<DataUserLinks> findUserLinksByLink(long linkId) {
        List<DataUserLinks> dataUserLinks =
            dslContext.select(UserLinks.USER_LINKS.USER_ID, UserLinks.USER_LINKS.LINKS_ID)
                .from(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.LINKS_ID.eq(linkId))
                .fetch().into(DataUserLinks.class);
        return dataUserLinks;
    }
}
