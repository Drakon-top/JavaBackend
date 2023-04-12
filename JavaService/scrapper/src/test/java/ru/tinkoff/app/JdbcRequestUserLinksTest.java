package ru.tinkoff.app;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.web.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.web.dto.db.DataUserLinks;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcRequestUserLinksTest extends JdbcRequestTableTest {

    public JdbcRequestUserLinksTest() throws URISyntaxException {
        super();
    }

    @Transactional
    @Rollback
    @Test
    public void addUserLink__addUserLinkInDB_CountUserLinkIncrement() {
        userTable.addUser(CHAT_ID, USER_NAME);
        DataLink link = linkTable.addLink(TEST_URL);
        List<DataUserLinks> listUserLinksWas = userLinksTable.findAllUserLinks();
        int was = listUserLinksWas.size();

        userLinksTable.addUserLink(CHAT_ID, link.getId());

        List<DataUserLinks> listUserLinks = userLinksTable.findAllUserLinks();
        assert (listUserLinks.size() > 0);
        DataUserLinks data = listUserLinks.get(0);
        assertAll(
                () -> assertEquals(listUserLinks.size(), was + 1),
                () -> assertNotNull(data),
                () -> assertEquals(CHAT_ID, data.getUserId()),
                () -> assertEquals(link.getId(), data.getLinksId())
        );
    }

    @Transactional
    @Rollback
    @Test
    public void removeTest__removeUserLinkInDB__CountUserLinkDecrement() {
        userTable.addUser(CHAT_ID, USER_NAME);
        DataLink link = linkTable.addLink(TEST_URL);
        userLinksTable.addUserLink(CHAT_ID, link.getId());
        List<DataUserLinks> listUserLinksWas = userLinksTable.findAllUserLinks();
        int was = listUserLinksWas.size();

        userLinksTable.removeLink(CHAT_ID, link.getId());

        List<DataUserLinks> listUserLinks = userLinksTable.findAllUserLinks();
        assertEquals( was - 1, listUserLinks.size());
    }

    @Transactional
    @Rollback
    @Test
    public void findAllUserLink__addLink_checkedFindThisLink() {
        userTable.addUser(CHAT_ID, USER_NAME);
        DataLink link = linkTable.addLink(TEST_URL);
        List<DataUserLinks> listUserLinksWas = userLinksTable.findAllUserLinks();
        assertEquals(listUserLinksWas.size(), 0);
        userLinksTable.addUserLink(CHAT_ID, link.getId());

        List<DataUserLinks> listUserLinks = userLinksTable.findAllUserLinks();

        assert (listUserLinks.size() == 1);
        DataUserLinks data = listUserLinks.get(0);
        assertAll(
                () -> assertNotNull(data),
                () -> assertEquals(CHAT_ID, data.getUserId()),
                () -> assertEquals(link.getId(), data.getLinksId())
        );
    }
}
