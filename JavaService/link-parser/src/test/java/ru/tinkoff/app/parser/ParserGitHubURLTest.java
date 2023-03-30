package ru.tinkoff.app.parser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.app.url.UrlDataGitHub;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParserGitHubURLTest {

    @Test
    void parseURL__transferredInvalidURL_URLDataGitHubIsNull() {
        // given
        ParserURL parserGitHubURL = new ParserGitHubURL();

        // when

        // then
        assertAll(
                () -> assertNull(parserGitHubURL.parseUrl("random text")),
                () -> assertNull(parserGitHubURL.parseUrl("https://github.com/")),
                () -> assertNull(parserGitHubURL.parseUrl("https://github.com/person/")),
                () -> assertNull(parserGitHubURL.parseUrl("https://github.com/person//")),
                () -> assertNull(parserGitHubURL.parseUrl("https://github.com/person///")),
                () -> assertNull(parserGitHubURL.parseUrl("github.com/person/rep/")),
                () -> assertNull(parserGitHubURL.parseUrl("https://stackoverflow.com/questions/1642028/"))
        );
    }

    @Test
    void parseURL__transferredValidURL_URLDataGitHubIsCorrect() {
        // given
        ParserURL parserGitHubURL = new ParserGitHubURL();

        // when

        // then
        assertAll(
                () -> assertEquals(parserGitHubURL.parseUrl("https://github.com/person/rep/////"),
                        new UrlDataGitHub("github.com", "person", "rep")),
                () -> assertEquals(parserGitHubURL.parseUrl("https://github.com/person/rep/"),
                        new UrlDataGitHub("github.com", "person", "rep")),
                () -> assertEquals(parserGitHubURL.parseUrl("https://github.com/o/r11//"),
                        new UrlDataGitHub("github.com", "o", "r11"))
        );
    }
}
