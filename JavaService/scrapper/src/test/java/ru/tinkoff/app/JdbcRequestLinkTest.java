package ru.tinkoff.app;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestLinkTable;
import ru.tinkoff.edu.java.scrapper.web.dto.DataLinkTable;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcRequestLinkTest extends JdbcRequestTableTest {
    private JdbcRequestLinkTable linkTable;
    private final String TEST_URL = "https://github.com/person/rep/";

    public JdbcRequestLinkTest() {
        linkTable = new JdbcRequestLinkTable(dataSource);
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
                () -> assertNotNull(dataLinkTable.getId()),
                () -> assertEquals(dataLinkTable.getUrl(), TEST_URL)
        );
    }

    @Transactional
    @Rollback
    @Test
    public void removeTest__removeLinkInDB__CountLinkDecrement() {
        linkTable.addLink(TEST_URL);
        List<DataLinkTable> listLinksWas = linkTable.findAllLinks();
        int wasSize = listLinksWas.size();

        linkTable.removeLink(TEST_URL);

        List<DataLinkTable> listLinks = linkTable.findAllLinks();
        assertEquals(listLinks.size(), wasSize - 1);
    }

    @Transactional
    @Rollback
    @Test
    public void findAllTest__addLink_checkedFindThisLink() {
        List<DataLinkTable> listLinksWas = linkTable.findAllLinks();
        assertEquals(listLinksWas.size(), 0);
        linkTable.addLink(TEST_URL);

        List<DataLinkTable> listLinks = linkTable.findAllLinks();

        assertEquals(listLinks.size(), 1);
        DataLinkTable dataLinkTable = listLinks.get(0);
        assertAll(
                () -> assertNotNull(dataLinkTable.getId()),
                () -> assertEquals(dataLinkTable.getUrl(), TEST_URL)
        );
    }

}
