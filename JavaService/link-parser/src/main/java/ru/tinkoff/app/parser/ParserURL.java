package ru.tinkoff.app.parser;

import java.util.Map;
import java.util.Optional;

public sealed interface ParserURL permits ParserGutHubURL, ParserStackOverflow {
    Optional<Map<String, String>> parseUrl(String url);
}
