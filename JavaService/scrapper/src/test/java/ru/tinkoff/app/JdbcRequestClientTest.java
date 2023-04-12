package ru.tinkoff.app;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestClientTable;
import ru.tinkoff.edu.java.scrapper.web.dto.DataLinkTable;
import ru.tinkoff.edu.java.scrapper.web.dto.DataUserTable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcRequestClientTest extends JdbcRequestTableTest {
    private JdbcRequestClientTable userTable;
    private final String USER_NAME = "Alex";
    private final Long CHAT_ID = 13424L;

    public JdbcRequestClientTest() {
        userTable = new JdbcRequestClientTable(dataSource);
    }

    @Transactional
    @Rollback
    @Test
    public void addUser__addUserInDB_CountUserIncrement() {
        List<DataUserTable> listUsers = userTable.findAllUsers();
        int wasSize = listUsers.size();

        userTable.addUser(CHAT_ID, USER_NAME);

        List<DataUserTable> listUsersNow = userTable.findAllUsers();
        assert (listUsersNow.size() > 0);
        DataUserTable dataUserTable = listUsersNow.get(0);
        assertAll(
                () -> assertEquals(wasSize + 1, listUsersNow.size()),
                () -> assertEquals(dataUserTable.getChatId(), CHAT_ID),
                () -> assertEquals(dataUserTable.getUserName(), USER_NAME)
        );
    }

    @Transactional
    @Rollback
    @Test
    public void removeTest__removeUserInDB__CountUserDecrement() {
        userTable.addUser(CHAT_ID, USER_NAME);
        List<DataUserTable> listUsersWas = userTable.findAllUsers();
        int wasSize = listUsersWas.size();

        userTable.removeUser(CHAT_ID);

        List<DataUserTable> listUsers = userTable.findAllUsers();
        assertEquals(listUsers.size(), wasSize - 1);
    }

    @Transactional
    @Rollback
    @Test
    public void findAllTest__addLink_checkedFindThisLink() {
        List<DataUserTable> listUsersWas = userTable.findAllUsers();
        assertEquals(listUsersWas.size(), 0);
        userTable.addUser(CHAT_ID, USER_NAME);

        List<DataUserTable> listUsers = userTable.findAllUsers();
        assertEquals(listUsers.size(), 1);
        DataUserTable dataUserTable = listUsers.get(0);
        assertAll(
                () -> assertEquals(dataUserTable.getChatId(), CHAT_ID),
                () -> assertEquals(dataUserTable.getUserName(), USER_NAME)
        );
    }

}