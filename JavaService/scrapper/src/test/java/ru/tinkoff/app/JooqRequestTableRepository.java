package ru.tinkoff.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.CloseableDSLContext;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.JdbcRequestUserLinksRepository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.JooqRequestClientRepository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.JooqRequestLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.JooqRequestUserLinksRepository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Client;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.ClientRecord;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.util.HashSet;
import java.util.Set;


@SpringBootTest
public class JooqRequestTableRepository extends IntegrationEnvironment {
    protected final URI TEST_URL = new URI("https://github.com/person/rep/");
    protected final String USER_NAME = "Alex";
    protected final Long CHAT_ID = 13424L;
    protected final JooqRequestClientRepository userTable;
    protected final JooqRequestLinkRepository linkTable;
    protected final JooqRequestUserLinksRepository userLinksTable;


    public JooqRequestTableRepository() throws URISyntaxException, SQLException {
        Connection connection = DriverManager.getConnection(POSTGRES_CONTAINER.getJdbcUrl(), POSTGRES_CONTAINER.getUsername(), POSTGRES_CONTAINER.getPassword());
        DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
        this.userTable = new JooqRequestClientRepository(context);
        this.linkTable = new JooqRequestLinkRepository(context);
        this.userLinksTable = new JooqRequestUserLinksRepository(context);
    }
}
