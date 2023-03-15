package ru.tinkoff.app.parser;

import ru.tinkoff.app.url.UrlData;


public sealed interface ParserURL permits ParserGitHubURL, ParserStackOverflow {
    UrlData parseUrl(String url);
}
