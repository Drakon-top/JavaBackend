package ru.tinkoff.edu.java.scrapper.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
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
