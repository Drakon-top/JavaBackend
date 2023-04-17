package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class JooqLinkService implements LinkService {

    private final DSLContext dsl;

    @Override
    public DataLink add(long tgChatId, URI url) {
        return null;
    }

    @Override
    public DataLink remove(long tgChatId, URI url) {
        return null;
    }

    @Override
    public Collection<DataLink> listAll(long tgChatId) {
        return null;
    }

    @Override
    public List<DataLinkWithInformation> listLongTimeUpdate() {
        return null;
    }

    @Override
    public List<DataUserLinks> findUserLinksByLinks(long idLink) {
        return null;
    }
}
