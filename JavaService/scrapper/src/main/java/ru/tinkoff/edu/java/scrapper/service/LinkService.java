package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.web.dto.db.DataLink;

import java.net.URI;
import java.util.Collection;

public interface LinkService {
    DataLink add(long tgChatId, URI url);
    DataLink remove(long tgChatId, URI url);
    Collection<DataLink> listAll(long tgChatId);
}
