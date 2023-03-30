package ru.tinkoff.app.parser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.app.url.UrlDataStackOverflow;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ParserStackOverflowURLTest {

    @Test
    void parseURL__transferredInvalidURL_URLDataGitHubIsNull() {
        // given
        ParserURL parserStackOverflowURL = new ParserStackOverflowURL();

        // when

        // then
        assertAll(
                () -> assertNull(parserStackOverflowURL.parseUrl("random text")),
                () -> assertNull(parserStackOverflowURL.parseUrl("https://stackoverflow.com/search?q=unsupported%20link")),
                () -> assertNull(parserStackOverflowURL.parseUrl("https://github.com/person/rep/")),
                () -> assertNull(parserStackOverflowURL.parseUrl("https://stackoverflow.com/questions/what-is-the-operator-in-c")),
                () -> assertNull(parserStackOverflowURL.parseUrl("https://stackoverflow.com/quest/1642028/")),
                () -> assertNull(parserStackOverflowURL.parseUrl("https://stackoverflow.com/questions/one/"))
        );
    }

    @Test
    void parseURL__transferredValidURL_URLDataGitHubIsCorrect() {
        // given
        ParserURL parserStackOverflowURL = new ParserStackOverflowURL();

        // when

        // then
        assertAll(
                () -> assertEquals(parserStackOverflowURL.parseUrl("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"),
                        new UrlDataStackOverflow("stackoverflow.com", 1642028)),
                () -> assertEquals(parserStackOverflowURL.parseUrl("https://stackoverflow.com/questions/1642028/"),
                        new UrlDataStackOverflow("stackoverflow.com", 1642028)),
                () -> assertEquals(parserStackOverflowURL.parseUrl("https://stackoverflow.com/questions/1642028/abracadabra"),
                        new UrlDataStackOverflow("stackoverflow.com", 1642028)),
                () -> assertEquals(parserStackOverflowURL.parseUrl("https://stackoverflow.com/questions/22"),
                        new UrlDataStackOverflow("stackoverflow.com", 22))
        );
    }
}
