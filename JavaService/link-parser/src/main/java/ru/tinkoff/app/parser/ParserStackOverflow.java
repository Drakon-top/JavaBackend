package ru.tinkoff.app.parser;

import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.app.url.UrlDataStackOverflow;

public final class ParserStackOverflow implements ParserURL {

    private final String QUESTION = "questions";
    private final String TYPE_Url = "stackoverflow.com";

    @Override
    public UrlData parseUrl(String url) {
        String[] args = url.split("/");
        if (args.length > 4 && args[2].equals(TYPE_Url) && args[3].equals(QUESTION)) {
            try {
                Integer id = Integer.parseInt(args[4]);
                return new UrlDataStackOverflow(TYPE_Url, id);
            } catch (NumberFormatException e) {
                // Bad url
                return null;
            }
        }
        return null;
    }
}
