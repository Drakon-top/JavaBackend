package ru.tinkoff.edu.java.scrapper.domain;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import ru.tinkoff.edu.java.scrapper.web.dto.DataUserLinksTable;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcRequestUserLinksTable {
    private final DataSource dataSource;

    public JdbcRequestUserLinksTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addUserLink(long chatId, long linkId) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                insert into user_links (user_id, links_id)
                values (?, ?)""", chatId, linkId
        ));
    }

    public void removeLink(long chatId, long linkId) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                   delete from user_links where user_id = ? and links_id = ?""", chatId, linkId
        ));
    }

    public List<DataUserLinksTable> findAllUserLinks() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query("select user_id, links_id from user_links", new BeanPropertyRowMapper<>(DataUserLinksTable.class));
    }
}
