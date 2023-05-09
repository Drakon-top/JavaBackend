package ru.tinkoff.edu.java.linkparser;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkparser.url.UrlData;

import java.util.List;

@Service
public class ParsingUrlService {
    public static UrlData getInfoAboutURL(String urlInput) {
        List<ParserURL> parsers = List.of(new ParserGitHubURL(), new ParserStackOverflowURL());
        for (ParserURL parser : parsers) {
            UrlData result = parser.parseUrl(urlInput);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
