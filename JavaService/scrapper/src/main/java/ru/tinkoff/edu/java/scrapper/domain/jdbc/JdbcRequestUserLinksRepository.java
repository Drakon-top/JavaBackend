package ru.tinkoff.edu.java.scrapper.domain.jdbc;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUserLinks;

@RequiredArgsConstructor
public class JdbcRequestUserLinksRepository {
    private final JdbcTemplate template;

    @Transactional
    public void addUserLink(long chatId, long linkId) {
        template.update("""
            insert into user_links (user_id, links_id)
            values (?, ?)""", chatId, linkId);
    }

    @Transactional
    public void removeLink(long chatId, long linkId) {
        template.update("""
            delete from user_links where user_id = ? and links_id = ?""", chatId, linkId);
    }

    public List<DataUserLinks> findAllUserLinks() {
        return template.query(
            "select user_id, links_id from user_links",
            new BeanPropertyRowMapper<>(DataUserLinks.class)
        );
    }

    public List<DataUserLinks> findUserLinksByUser(long chatId) {
        return template.query("""
            select user_id, links_id
            from user_links
            where user_id = ?""", new BeanPropertyRowMapper<>(DataUserLinks.class), chatId);
    }

    public List<DataUserLinks> findUserLinksByLink(long linkId) {
        return template.query("""
            select user_id, links_id
            from user_links
            where links_id = ?""", new BeanPropertyRowMapper<>(DataUserLinks.class), linkId);
    }
}
