package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.enums.TypeClient;
import ru.tinkoff.edu.java.linkparser.url.UrlData;
import ru.tinkoff.edu.java.linkparser.url.UrlDataStackOverflow;

public final class ParserStackOverflowURL implements ParserURL {

    private static final String QUESTIONS = "questions";
    private static final String TYPE_URL = "stackoverflow.com";

    private final TypeClient typeClient = TypeClient.STACKOVERFLOW;
    private final int minCountArgs = 4;
    private final int numberType = 2;
    private final int numberQuestion = 3;

    @Override
    public UrlData parseUrl(String url) {
        String[] args = url.split("/");
        if (args.length > minCountArgs && args[numberType].equals(TYPE_URL) && args[numberQuestion].equals(QUESTIONS)) {
            try {
                Long id = Long.parseLong(args[minCountArgs]);
                return new UrlDataStackOverflow(typeClient, id);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
