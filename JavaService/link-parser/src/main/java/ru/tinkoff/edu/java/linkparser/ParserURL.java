package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.url.UrlData;


public sealed interface ParserURL permits ParserGitHubURL, ParserStackOverflowURL {
    UrlData parseUrl(String url);
}
