package ru.tinkoff.app;

import ru.tinkoff.app.parser.ParserURL;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Optional;

public class ParserURLApplication {
    public static Map<String, String> getInfoAboutURL(String urlInput) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        Map<String, ParserURL> parsers = context.getBeansOfType(ParserURL.class);
        for (ParserURL parser : parsers.values()) {
            Optional<Map<String, String>> result = parser.parseUrl(urlInput);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return null;
    }
}
