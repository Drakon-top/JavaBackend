package ru.tinkoff.edu.java.scrapper.web.controller;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;

@RestController
public class ChatsController {

    private final TgChatService tgChatService;

    public ChatsController(TgChatService tgChatService) {
        this.tgChatService = tgChatService;
    }

    @PostMapping("/tg-chat/{id}")
    public void registerChat(@PathVariable("id") long id, @RequestParam("User_Name") String userName) {
        System.out.println("register");
        tgChatService.register(id, userName);
    }

    @DeleteMapping("/tg-chat/{id}")
    public void deleteChat(@PathVariable("id") long id) {
        tgChatService.unregister(id);
    }
}
