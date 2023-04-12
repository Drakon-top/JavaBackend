package ru.tinkoff.edu.java.scrapper.service.jdbc;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestLinkTable;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestUserLinksTable;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.web.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.web.dto.db.DataUserLinks;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JdbcLinkService implements LinkService {

    private JdbcRequestLinkTable jdbcRequestLink;
    private JdbcRequestUserLinksTable jdbcRequestUserLinks;

    public JdbcLinkService(JdbcRequestLinkTable jdbcRequestLink, JdbcRequestUserLinksTable jdbcRequestUserLinks) {
        this.jdbcRequestLink = jdbcRequestLink;
        this.jdbcRequestUserLinks = jdbcRequestUserLinks;
    }

    @Override
    public DataLink add(long tgChatId, URI url) {
        DataLink dataLink = jdbcRequestLink.addLink(url);
        jdbcRequestUserLinks.addUserLink(tgChatId, dataLink.getId());
        return dataLink;
    }

    @Override
    public DataLink remove(long tgChatId, URI url) {
        DataLink dataLink = jdbcRequestLink.removeLink(url);
        jdbcRequestUserLinks.removeLink(tgChatId, dataLink.getId());
        List<DataUserLinks> dataUserLinksList = jdbcRequestUserLinks.findUserLinksByLink(dataLink.getId());
        if (dataUserLinksList.size() == 0) {
            jdbcRequestLink.removeLink(url);
        }
        return dataLink;
    }

    @Override
    public Collection<DataLink> listAll(long tgChatId) {
        List<DataUserLinks> dataUserLinksList = jdbcRequestUserLinks.findUserLinksByUser(tgChatId);
        Set<Long> setLinksId = dataUserLinksList.stream().map(DataUserLinks::getLinksId).collect(Collectors.toSet());
        List<DataLink> dataLinks = jdbcRequestLink.findAllLinks();
        return dataLinks.stream().filter(x -> setLinksId.contains(x.getId())).toList();
    }
}
