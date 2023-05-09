package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.enums.TypeClient;
import ru.tinkoff.edu.java.linkparser.url.UrlData;
import ru.tinkoff.edu.java.linkparser.url.UrlDataGitHub;

public final class ParserGitHubURL implements ParserURL {

    private final String TYPE_URL = "github.com";
    private final TypeClient typeClient = TypeClient.GITHUB;
    @Override
    public UrlData parseUrl(String url) {
        String[] args = url.split("/");
        if (args.length > 4 && args[2].equals(TYPE_URL)) {
            return new UrlDataGitHub(typeClient, args[3], args[4]);
        }
        return null;
    }
}
