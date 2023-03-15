package ru.tinkoff.app.parser;

import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.app.url.UrlDataGitHub;

public final class ParserGitHubURL implements ParserURL {

    @Override
    public UrlData parseUrl(String url) {
        String[] args = url.split("/");
        String typeUrl = "github.com";
        if (args.length > 4 && args[2].equals(typeUrl)) {
            return new UrlDataGitHub(typeUrl, args[3], args[4]);
        }
        return null;
    }
}
