package ru.tinkoff.edu.java.bot.controller;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ChatsController {

    @PostMapping("/tg-chat/{id}")
    public void registerChat(@PathVariable("id") long id) {
        return;
    }

    @DeleteMapping("/tg-chat/{id}")
    public void deleteChat(@PathVariable("id") long id) {
        return;
    }
}
