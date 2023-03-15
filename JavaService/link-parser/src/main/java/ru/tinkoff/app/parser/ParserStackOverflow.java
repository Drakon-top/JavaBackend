package ru.tinkoff.app.parser;

import ru.tinkoff.app.url.UrlData;
import ru.tinkoff.app.url.UrlDataStackOverflow;

public final class ParserStackOverflow implements ParserURL {

    @Override
    public UrlData parseUrl(String url) {
        String[] args = url.split("/");
        String nameArgs3 = "questions";
        String typeUrl = "stackoverflow.com";
        if (args.length > 4 && args[2].equals(typeUrl) && args[3].equals(nameArgs3)) {
            try {
                Integer id = Integer.parseInt(args[4]);
                return new UrlDataStackOverflow(typeUrl, id);
            } catch (NumberFormatException e) {
                // Bad url
                return null;
            }
        }
        return null;
    }
}
