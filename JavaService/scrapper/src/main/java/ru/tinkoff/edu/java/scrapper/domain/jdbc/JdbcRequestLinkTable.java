package ru.tinkoff.edu.java.scrapper.domain.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class JdbcRequestLinkTable {
    private final DataSource dataSource;

    public JdbcRequestLinkTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public DataLink addLink(URI link, OffsetDateTime lastEditTime, Integer countAnswer) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        OffsetDateTime timeNow = OffsetDateTime.now();
        Long idLink = template.queryForObject("""
                insert into link(url, last_update, last_edit_time, count_commit_or_question)
                values (?, ?, ?, ?)
                returning id""", Long.class, link.toString(), timeNow, lastEditTime, countAnswer
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

    @Transactional
    public List<DataLinkWithInformation> findLinkNotUpdateLongTime(int countLink) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query("select id, url, last_update, last_edit_time, count_commit_or_question from link order by last_update limit ? ", (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String url = rs.getString("url");
            OffsetDateTime lastUpdate = rs.getObject("last_update", OffsetDateTime.class);
            OffsetDateTime lastEditTime = rs.getObject("last_edit_time", OffsetDateTime.class);
            Integer countAnswer = rs.getObject("count_commit_or_question", Integer.class);
            try {
                return new DataLinkWithInformation(id, new URI(url), lastUpdate, lastEditTime, countAnswer);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }, countLink);
    }
}
