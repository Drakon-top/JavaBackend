package ru.tinkoff.edu.java.scrapper.domain;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import ru.tinkoff.edu.java.scrapper.web.dto.DataUserTable;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcClientTable {

    private final DataSource dataSource;

    public JdbcClientTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addUser(Long chatId, String userName) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                insert into client (chat_id, user_name)
                values (?, ?)""", chatId, userName
        ));
    }

    public void removeUser(Long chatId) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new JdbcTransactionManager(dataSource));
        JdbcTemplate template = new JdbcTemplate(dataSource);
        transactionTemplate.execute(a -> template.update("""
                   delete from client where chat_id = ?""", chatId
        ));
    }

    public List<DataUserTable> findAllUsers() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query("select chat_id, user_name from client", new BeanPropertyRowMapper<>(DataUserTable.class));
    }
}
