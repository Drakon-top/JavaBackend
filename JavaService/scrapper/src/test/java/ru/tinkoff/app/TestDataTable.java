package ru.tinkoff.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestDataTable extends IntegrationEnvironment {

    @Test
    void database__startMigration_databaseRun() {
        assertNotNull(database);
    }
}
