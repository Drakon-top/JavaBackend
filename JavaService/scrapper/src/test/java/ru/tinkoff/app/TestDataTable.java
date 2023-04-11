package ru.tinkoff.app;

import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestDataTable extends IntegrationEnvironment {

    @Test
    void database__startMigration_databaseRun() throws DatabaseException {
        assertNotNull(database);
        try (Connection connection = DriverManager.getConnection(POSTGRES_CONTAINER.getJdbcUrl(), POSTGRES_CONTAINER.getUsername(), POSTGRES_CONTAINER.getPassword())) {
            ResultSet resultSet = connection.getMetaData().getTables(null, null,
                    "%", new String[]{"TABLE"});
            Set<String> tableName = new HashSet<>();
            while (resultSet.next()) {
                tableName.add(resultSet.getString("TABLE_NAME"));
            }
            assertAll(
                    () -> assertTrue(tableName.contains("link")),
                    () -> assertTrue(tableName.contains("client")),
                    () -> assertTrue(tableName.contains("userlinks"))
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
