package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Records;
import org.jooq.Result;
import org.jooq.User;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Component;
import ru.tinkoff.app.ParsingUrlService;
import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Client;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.UserLinks;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.LinkRecord;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.UserLinksRecord;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.web.ClientManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JooqLinkService implements LinkService {

    private final DSLContext dsl;
    private final ClientManager clientManager;

    @Override
    public DataLink add(long tgChatId, URI url) {
        UrlData urlData = ParsingUrlService.getInfoAboutURL(url.toString());
        if (urlData == null) {
            return null;
        }
        OffsetDateTime timeEditLast = clientManager.timeEditLinkForType(urlData);
        Integer count = clientManager.getCountAnswer(urlData);

        Long idLink = Long.valueOf(dsl.insertInto(Link.LINK)
                .set(Link.LINK.URL, url.toString())
                .set(Link.LINK.LAST_EDIT_TIME, timeEditLast.toLocalDateTime())
                .set(Link.LINK.LAST_UPDATE, OffsetDateTime.now().toLocalDateTime())
                .set(Link.LINK.COUNT_COMMIT_OR_QUESTION, count)
                .returning(Link.LINK.ID)
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Error inserting entity"))
                .get(Link.LINK.ID));
        dsl.insertInto(UserLinks.USER_LINKS)
                .set(new UserLinksRecord(tgChatId, idLink));
        return new DataLink(idLink, url);
    }

    @Override
    public DataLink remove(long tgChatId, URI url) {
        Long idLink = Long.valueOf(dsl.select(Link.LINK)
                .where(Link.LINK.URL.eq(url.toString()))
                .fetchOne(Link.LINK.ID));
        DataLink dataLink = new DataLink(idLink, url);
        dsl.delete(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.LINKS_ID.eq(idLink), UserLinks.USER_LINKS.USER_ID.eq(tgChatId));
        return dataLink;
    }

    @Override
    public Collection<DataLink> listAll(long tgChatId) {
        Set<Long> setIdLink = dsl.select(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.LINKS_ID.eq(tgChatId))
                .fetch(Link.LINK.ID).stream().map(x -> Long.valueOf(x)).collect(Collectors.toSet());
        Collection<DataLink> linkRecords = dsl.selectFrom(Link.LINK)
                .where(Link.LINK.ID.in(setIdLink))
                .fetch().stream().map(x -> {
                    try {
                        return new DataLink(Long.valueOf(x.getId()), new URI(x.getUrl()));
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
        return linkRecords;
    }

    @Override
    public List<DataLinkWithInformation> listLongTimeUpdate() {
        return dsl.selectFrom(Link.LINK)
                .fetch().stream().map(x -> {
                    try {
                        return new DataLinkWithInformation(Long.valueOf(x.getId()), new URI(x.getUrl()),
                                x.getLastUpdate().atOffset(ZoneOffset.ofHours(3)), x.getLastEditTime().atOffset(ZoneOffset.ofHours(3)), x.getCountCommitOrQuestion());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    @Override
    public List<DataUserLinks> findUserLinksByLinks(long idLink) {
        return dsl.selectFrom(UserLinks.USER_LINKS)
                .where(UserLinks.USER_LINKS.LINKS_ID.eq(idLink))
                .fetch().stream().map(x -> new DataUserLinks(x.getUserId(), idLink)).toList();
    }
}
