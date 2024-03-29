package ru.tinkoff.edu.java.linkparser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.linkparser.enums.TypeClient;
import ru.tinkoff.edu.java.linkparser.url.UrlDataGitHub;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParserGitHubURLTest {

    private ParserURL parserGitHubURL = new ParserGitHubURL();

    private final TypeClient typeClient = TypeClient.GITHUB;

    @ParameterizedTest
    @ValueSource(strings = {"random text", "https://github.com/", "https://github.com/person/",
            "https://github.com/person//", "https://github.com/person///", "github.com/person/rep/",
            "https://stackoverflow.com/questions/1642028/}"})
    void parseURL__transferredInvalidURL_URLDataGitHubIsNull(String invalidUrl) {
        assertNull(parserGitHubURL.parseUrl(invalidUrl));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "https://github.com/person/rep/////, person, rep",
            "https://github.com/person/rep/, person, rep",
            "https://github.com/o/r11//, o, r11",
    })
    void parseURL__transferredValidURL_URLDataGitHubIsCorrect(String validUrl, String userName, String rep) {

        assertEquals(parserGitHubURL.parseUrl(validUrl),
                new UrlDataGitHub(typeClient, userName, rep));
    }
}
