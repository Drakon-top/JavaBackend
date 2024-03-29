package ru.tinkoff.edu.java.linkparser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.linkparser.enums.TypeClient;
import ru.tinkoff.edu.java.linkparser.url.UrlDataStackOverflow;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ParserStackOverflowURLTest {

    private ParserURL parserStackOverflowURL = new ParserStackOverflowURL();

    private final TypeClient typeClient = TypeClient.STACKOVERFLOW;

    @ParameterizedTest
    @ValueSource(strings = {"random text", "https://stackoverflow.com/search?q=unsupported%20link",
            "https://github.com/person/rep/", "https://stackoverflow.com/questions/what-is-the-operator-in-c",
            "https://stackoverflow.com/quest/1642028/", "https://stackoverflow.com/questions/one/"
    })
    void parseURL__transferredInvalidURL_URLDataGitHubIsNull(String invalidUrl) {
        assertNull(parserStackOverflowURL.parseUrl(invalidUrl));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c, 1642028",
            "https://stackoverflow.com/questions/1642028/abracadabra, 1642028",
            "https://stackoverflow.com/questions/1642028/, 1642028",
            "https://stackoverflow.com/questions/22, 22"
    })
    void parseURL__transferredValidURL_URLDataGitHubIsCorrect(String validUrl, Long id) {
        assertEquals(parserStackOverflowURL.parseUrl(validUrl),
                new UrlDataStackOverflow(typeClient, id));
    }
}
