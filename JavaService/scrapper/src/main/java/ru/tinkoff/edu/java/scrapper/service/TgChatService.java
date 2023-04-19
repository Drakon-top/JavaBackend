package ru.tinkoff.edu.java.scrapper.service;

public interface TgChatService {
    void register(long tgChatId, String userName);
    void unregister(long tgChatId);
}
