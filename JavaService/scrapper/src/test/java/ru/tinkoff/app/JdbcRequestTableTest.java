package ru.tinkoff.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestClientTable;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestLinkTable;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestUserLinksTable;

import javax.sql.DataSource;

public class JdbcRequestTableTest extends IntegrationEnvironment {
    protected final DataSource dataSource;
    protected final String TEST_URL = "https://github.com/person/rep/";
    protected final String USER_NAME = "Alex";
    protected final Long CHAT_ID = 13424L;
    protected final JdbcRequestClientTable userTable;
    protected final JdbcRequestLinkTable linkTable;
    protected final JdbcRequestUserLinksTable userLinksTable;

    public JdbcRequestTableTest() {
        dataSource = initDatasource();
        userTable = new JdbcRequestClientTable(dataSource);
        linkTable = new JdbcRequestLinkTable(dataSource);
        userLinksTable = new JdbcRequestUserLinksTable(dataSource);
    }

    private DataSource initDatasource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(POSTGRES_CONTAINER.getJdbcUrl());
        hikariConfig.setUsername(POSTGRES_CONTAINER.getUsername());
        hikariConfig.setPassword(POSTGRES_CONTAINER.getPassword());
        hikariConfig.setAutoCommit(false);

        return new HikariDataSource(hikariConfig);
    }
}
