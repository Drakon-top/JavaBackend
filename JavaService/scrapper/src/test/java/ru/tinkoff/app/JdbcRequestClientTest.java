package ru.tinkoff.app;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.web.dto.DataUserTable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcRequestClientTest extends JdbcRequestTableTest {

    public JdbcRequestClientTest() {
        super();
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
                () -> assertNotNull(dataUserTable),
                () -> assertEquals(CHAT_ID, dataUserTable.getChatId()),
                () -> assertEquals(USER_NAME, dataUserTable.getUserName())
        );
    }

    @Transactional
    @Rollback
    @Test
    public void removeUser__removeUserInDB__CountUserDecrement() {
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
    public void findAllUser__addLink_checkedFindThisLink() {
        List<DataUserTable> listUsersWas = userTable.findAllUsers();
        assertEquals(listUsersWas.size(), 0);
        userTable.addUser(CHAT_ID, USER_NAME);

        List<DataUserTable> listUsers = userTable.findAllUsers();
        assertEquals(listUsers.size(), 1);
        DataUserTable dataUserTable = listUsers.get(0);
        assertAll(
                () -> assertNotNull(dataUserTable),
                () -> assertEquals(dataUserTable.getChatId(), CHAT_ID),
                () -> assertEquals(dataUserTable.getUserName(), USER_NAME)
        );
    }

}