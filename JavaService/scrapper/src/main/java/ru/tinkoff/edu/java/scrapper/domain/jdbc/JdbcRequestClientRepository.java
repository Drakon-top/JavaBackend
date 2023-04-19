package ru.tinkoff.edu.java.scrapper.domain.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.db.DataUser;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcRequestClientRepository {

    private final JdbcTemplate template;

    @Transactional
    public void addUser(Long chatId, String userName) {
        if (!userAlreadyRegister(chatId)) {
            template.update("""
                insert into client (chat_id, user_name)
                values (?, ?)""", chatId, userName
            );
        }
    }

    @Transactional
    public void removeUser(Long chatId) {
        template.update("""
                delete from client where chat_id = ?""", chatId
        );
    }

    public List<DataUser> findAllUsers() {
        return template.query("select chat_id, user_name from client", new BeanPropertyRowMapper<>(DataUser.class));
    }

    public boolean userAlreadyRegister(Long chatId) {
        Integer count = template.queryForObject("""
                select count(chat_id) from client
                where chat_id = ?""", Integer.class, chatId
        );
        return count > 0;
    }
}
