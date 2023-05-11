package ru.tinkoff.edu.java.scrapper.service;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;


public interface LinkService {
    DataLink add(long tgChatId, URI url);

    DataLink remove(long tgChatId, URI url);

    Collection<DataLink> listLinkAll(long tgChatId);

    List<DataLinkWithInformation> listLongTimeUpdate();

    List<DataUserLinks> findUserLinksByLinks(long idLink);
}
