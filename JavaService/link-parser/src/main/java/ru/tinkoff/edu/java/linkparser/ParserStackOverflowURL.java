package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.enums.TypeClient;
import ru.tinkoff.edu.java.linkparser.url.UrlData;
import ru.tinkoff.edu.java.linkparser.url.UrlDataStackOverflow;

public final class ParserStackOverflowURL implements ParserURL {

    private final String QUESTIONS = "questions";
    private final String TYPE_URL = "stackoverflow.com";

    private final TypeClient typeClient = TypeClient.STACKOVERFLOW;

    @Override
    public UrlData parseUrl(String url) {
        String[] args = url.split("/");
        if (args.length > 4 && args[2].equals(TYPE_URL) && args[3].equals(QUESTIONS)) {
            try {
                Long id = Long.parseLong(args[4]);
                return new UrlDataStackOverflow(typeClient, id);
            } catch (NumberFormatException e) {
                // Bad url
                return null;
            }
        }
        return null;
    }
}
