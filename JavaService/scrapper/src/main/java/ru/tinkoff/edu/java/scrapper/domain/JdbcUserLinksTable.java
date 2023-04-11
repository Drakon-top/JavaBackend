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
public class JdbcUserLinksTable {
    private final DataSource dataSource;

    public JdbcUserLinksTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addUserLink(long chatId, long linkId) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                insert into userlinks (user_id, links_id)
                values (?, ?)""", chatId, linkId
        ));
    }

    public void removeLink(long chatId, long linkId) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                   delete from userlinks where user_id = ? and links_id = ?""", chatId, linkId
        ));
    }

    public List<DataUserLinksTable> findAllLinks() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query("select id, user_id, links_id from userlinks", new BeanPropertyRowMapper<>(DataUserLinksTable.class));
    }
}
