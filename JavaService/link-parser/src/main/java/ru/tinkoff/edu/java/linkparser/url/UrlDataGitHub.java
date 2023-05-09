package ru.tinkoff.edu.java.linkparser.url;

import ru.tinkoff.edu.java.linkparser.enums.TypeClient;

public record UrlDataGitHub(TypeClient typeUrl, String userName, String repository) implements UrlData {
    @Override
    public TypeClient getType() {
        return typeUrl;
    }
}
