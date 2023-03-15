package ru.tinkoff.app;

import ru.tinkoff.app.parser.ParserGitHubURL;
import ru.tinkoff.app.parser.ParserStackOverflow;
import ru.tinkoff.app.parser.ParserURL;
import ru.tinkoff.app.url.UrlData;

import java.util.List;

public class ConvertationUrlToData {
    public static UrlData getInfoAboutURL(String urlInput) {
        List<ParserURL> parsers = List.of(new ParserGitHubURL(), new ParserStackOverflow());
        for (ParserURL parser : parsers) {
            UrlData result = parser.parseUrl(urlInput);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
