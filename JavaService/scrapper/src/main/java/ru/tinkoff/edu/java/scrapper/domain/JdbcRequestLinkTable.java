package ru.tinkoff.edu.java.scrapper.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class JdbcRequestLinkTable {
    private final DataSource dataSource;

    public JdbcRequestLinkTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public DataLink addLink(URI link) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Long idLink = template.queryForObject("""
                insert into link(url)
                values (?)
                returning id""", Long.class, link.toString()
        );
        return new DataLink(idLink, link);
    }

    @Transactional
    public DataLink removeLink(URI link) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Long idLink = template.queryForObject("""
                delete from link where url = ?
                RETURNING id""", Long.class, link.toString()
        );
        return new DataLink(idLink, link);
    }

    @Transactional
    public List<DataLink> findAllLinks() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query("select id, url from link", (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String url = rs.getString("url");
            try {
                return new DataLink(id, new URI(url));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
