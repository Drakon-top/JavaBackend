package ru.tinkoff.edu.java.linkparser.url;

import ru.tinkoff.edu.java.linkparser.enums.TypeClient;

public record UrlDataStackOverflow(TypeClient typeUrl, Long idQuestion) implements UrlData {
    @Override
    public TypeClient getType() {
        return typeUrl;
    }
}
