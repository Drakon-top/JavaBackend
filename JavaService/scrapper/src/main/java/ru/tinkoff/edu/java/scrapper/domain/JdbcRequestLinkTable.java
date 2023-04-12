package ru.tinkoff.edu.java.scrapper.domain;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import ru.tinkoff.edu.java.scrapper.web.dto.DataLinkTable;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcRequestLinkTable {
    private final DataSource dataSource;

    public JdbcRequestLinkTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLink(String link) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                insert into link (url)
                values (?)""", link
        ));
    }

    public void removeLink(String link) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                   delete from link where url = ?""", link
        ));
    }

    public List<DataLinkTable> findAllLinks() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query("select id, url from link", new BeanPropertyRowMapper<>(DataLinkTable.class));
    }
}
