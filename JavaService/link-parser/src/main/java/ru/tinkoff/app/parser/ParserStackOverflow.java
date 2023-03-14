package ru.tinkoff.app.parser;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public final class ParserStackOverflow implements ParserURL {

    @Override
    public Optional<Map<String, String>> parseUrl(String url) {
        String[] args = url.split("/");
        Map<String, String> map = new HashMap<>();
        Optional<Map<String, String>> optional = Optional.empty();
        if (args.length > 4 && args[2].equals("stackoverflow.com") && args[3].equals("questions")) {
            try {
                Integer.parseInt(args[4]);
                map.put("type", "stackoverflow");
                map.put("id", args[4]);
                optional = Optional.of(map);
            } catch (NumberFormatException e) {
                // Bad url
            }
        }
        return optional;
    }
}
