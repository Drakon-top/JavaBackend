package ru.tinkoff.app;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestLinkTable;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestUserLinksTable;
import ru.tinkoff.edu.java.scrapper.web.dto.DataLinkTable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcRequestUserLinksTest extends JdbcRequestTableTest {
    private JdbcRequestUserLinksTable userLinksTable;
    private final String TEST_URL = "https://github.com/person/rep/";

    public JdbcRequestUserLinksTest() {
        userLinksTable = new JdbcRequestUserLinksTable(dataSource);
    }

    @Transactional
    @Rollback
    @Test
    public void addLink__addLinkInDB_CountLinkIncrement() {

    }

    @Transactional
    @Rollback
    @Test
    public void removeTest__removeLinkInDB__CountLinkDecrement() {

    }

    @Transactional
    @Rollback
    @Test
    public void findAllTest__addLink_checkedFindThisLink() {

    }
}
