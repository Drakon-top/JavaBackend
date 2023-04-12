package ru.tinkoff.app;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcRequestLinkTest extends JdbcRequestTableTest {

    public JdbcRequestLinkTest() throws URISyntaxException {
        super();
    }

    @Transactional
    @Rollback
    @Test
    public void addLink__addLinkInDB_CountLinkIncrement() {
        List<DataLink> listLinks = linkTable.findAllLinks();
        int wasSize = listLinks.size();

        DataLink link = linkTable.addLink(TEST_URL);

        List<DataLink> listLinksNow = linkTable.findAllLinks();
        assert (listLinksNow.size() > 0);
        DataLink dataLink = listLinksNow.get(0);
        assertAll(
                () -> assertEquals(TEST_URL, link.getUrl()),
                () -> assertEquals(wasSize + 1, listLinksNow.size()),
                () -> assertNotNull(dataLink),
                () -> assertNotNull(dataLink.getId()),
                () -> assertEquals(TEST_URL, dataLink.getUrl())
        );
    }

    @Transactional
    @Rollback
    @Test
    public void removeLink__removeLinkInDB__CountLinkDecrement() {
        DataLink linkWas = linkTable.addLink(TEST_URL);
        List<DataLink> listLinksWas = linkTable.findAllLinks();
        int wasSize = listLinksWas.size();

        DataLink linkNow = linkTable.removeLink(TEST_URL);

        List<DataLink> listLinks = linkTable.findAllLinks();
        assertAll(
                () -> assertEquals(linkWas, linkNow),
                () -> assertEquals(wasSize - 1, listLinks.size())
        );
    }

    @Transactional
    @Rollback
    @Test
    public void findAllLink__addLink_checkedFindThisLink() {
        List<DataLink> listLinksWas = linkTable.findAllLinks();
        assertEquals(0, listLinksWas.size());
        linkTable.addLink(TEST_URL);

        List<DataLink> listLinks = linkTable.findAllLinks();

        assertEquals(listLinks.size(), 1);
        DataLink dataLink = listLinks.get(0);
        assertAll(
                () -> assertNotNull(dataLink),
                () -> assertNotNull(dataLink.getId()),
                () -> assertEquals(TEST_URL, dataLink.getUrl())
        );
    }

}
