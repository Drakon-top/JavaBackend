package ru.tinkoff.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.tinkoff.edu.java.scrapper.domain.JdbcRequestLinkTable;

import javax.sql.DataSource;

public class JdbcRequestTableTest extends IntegrationEnvironment {
    protected final DataSource dataSource = initDatasource();

    private DataSource initDatasource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(POSTGRES_CONTAINER.getJdbcUrl());
        hikariConfig.setUsername(POSTGRES_CONTAINER.getUsername());
        hikariConfig.setPassword(POSTGRES_CONTAINER.getPassword());
        hikariConfig.setAutoCommit(false);

        return new HikariDataSource(hikariConfig);
    }
}
