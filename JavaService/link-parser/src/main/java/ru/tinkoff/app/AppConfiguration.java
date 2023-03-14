package ru.tinkoff.app;

import ru.tinkoff.app.parser.ParserGutHubURL;
import ru.tinkoff.app.parser.ParserStackOverflow;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        ParserGutHubURL.class,
        ParserStackOverflow.class
})
public class AppConfiguration {
}