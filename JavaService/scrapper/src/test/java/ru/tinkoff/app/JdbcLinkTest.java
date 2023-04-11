package ru.tinkoff.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.database.DatabaseConnection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.JdbcLinkTable;
import ru.tinkoff.edu.java.scrapper.web.dto.DataLinkTable;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JdbcLinkTest extends IntegrationEnvironment {
    private final DataSource dataSource;
    private JdbcLinkTable linkTable;

    public JdbcLinkTest() {
        dataSource = initDatasource();
        linkTable = new JdbcLinkTable(dataSource);
    }

    private DataSource initDatasource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(POSTGRES_CONTAINER.getJdbcUrl());
        hikariConfig.setUsername(POSTGRES_CONTAINER.getUsername());
        hikariConfig.setPassword(POSTGRES_CONTAINER.getPassword());

        return new HikariDataSource(hikariConfig);
    }

    @Transactional
    @Rollback
    @Test
    public void addLink() throws SQLException {
        List<DataLinkTable> listLinks = linkTable.findAllLinks();
        assert (listLinks.size() == 0);

        String testUrl = "https://github.com/person/rep/";
        linkTable.addLink(testUrl);

        listLinks = linkTable.findAllLinks();
        assert (listLinks.size() == 1);
        DataLinkTable dataLinkTable = listLinks.get(0);
        assertAll(
                () -> assertNotNull(dataLinkTable.getId()),
                () -> assertEquals(dataLinkTable.getUrl(), testUrl)
        );
    }

    @Transactional
    @Rollback
    public void removeTest() {
    }
}
