package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.enums.TypeClient;
import ru.tinkoff.edu.java.linkparser.url.UrlData;
import ru.tinkoff.edu.java.linkparser.url.UrlDataGitHub;

public final class ParserGitHubURL implements ParserURL {

    private static final String TYPE_URL = "github.com";
    private final TypeClient typeClient = TypeClient.GITHUB;
    private final int minCountArgs = 4;
    private final int numberType = 2;
    private final int numberUserName = 3;

    @Override
    public UrlData parseUrl(String url) {
        String[] args = url.split("/");
        if (args.length > minCountArgs && args[numberType].equals(TYPE_URL)) {
            return new UrlDataGitHub(typeClient, args[numberUserName], args[minCountArgs]);
        }
        return null;
    }
}
