package ru.tinkoff.app.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ParserGutHubURL implements ParserURL {
    @Override
    public Optional<Map<String, String>> parseUrl(String url) {
        String[] args = url.split("/");
        Map<String, String> map = new HashMap<>();
        Optional<Map<String, String>> optional = Optional.empty();
        if (args.length > 4 && args[2].equals("github.com")) {
            map.put("type", "github");
            map.put("user", args[3]);
            map.put("repository", args[4]);
            optional = Optional.of(map);
        }
        return optional;
    }
}
