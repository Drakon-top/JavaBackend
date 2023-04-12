package ru.tinkoff.app;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.web.dto.DataLinkTable;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcRequestLinkTest extends JdbcRequestTableTest {

    public JdbcRequestLinkTest() {
        super();
    }

    @Transactional
    @Rollback
    @Test
    public void addLink__addLinkInDB_CountLinkIncrement() {
        List<DataLinkTable> listLinks = linkTable.findAllLinks();
        int wasSize = listLinks.size();

        linkTable.addLink(TEST_URL);

        List<DataLinkTable> listLinksNow = linkTable.findAllLinks();
        assert (listLinksNow.size() > 0);
        DataLinkTable dataLinkTable = listLinksNow.get(0);
        assertAll(
                () -> assertEquals(wasSize + 1, listLinksNow.size()),
                () -> assertNotNull(dataLinkTable),
                () -> assertNotNull(dataLinkTable.getId()),
                () -> assertEquals(TEST_URL, dataLinkTable.getUrl())
        );
    }

    @Transactional
    @Rollback
    @Test
    public void removeLink__removeLinkInDB__CountLinkDecrement() {
        linkTable.addLink(TEST_URL);
        List<DataLinkTable> listLinksWas = linkTable.findAllLinks();
        int wasSize = listLinksWas.size();

        linkTable.removeLink(TEST_URL);

        List<DataLinkTable> listLinks = linkTable.findAllLinks();
        assertEquals(wasSize - 1, listLinks.size());
    }

    @Transactional
    @Rollback
    @Test
    public void findAllLink__addLink_checkedFindThisLink() {
        List<DataLinkTable> listLinksWas = linkTable.findAllLinks();
        assertEquals(0, listLinksWas.size());
        linkTable.addLink(TEST_URL);

        List<DataLinkTable> listLinks = linkTable.findAllLinks();

        assertEquals(listLinks.size(), 1);
        DataLinkTable dataLinkTable = listLinks.get(0);
        assertAll(
                () -> assertNotNull(dataLinkTable),
                () -> assertNotNull(dataLinkTable.getId()),
                () -> assertEquals(TEST_URL, dataLinkTable.getUrl())
        );
    }

}
