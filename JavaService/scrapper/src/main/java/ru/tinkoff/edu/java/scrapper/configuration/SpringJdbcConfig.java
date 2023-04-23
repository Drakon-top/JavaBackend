package ru.tinkoff.edu.java.scrapper.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.Row;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLink;
import ru.tinkoff.edu.java.scrapper.dto.db.DataLinkWithInformation;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

@Configuration
public class SpringJdbcConfig {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/scrapper";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";


    @Bean
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DATABASE_URL);
        hikariConfig.setUsername(USERNAME);
        hikariConfig.setPassword(PASSWORD);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
