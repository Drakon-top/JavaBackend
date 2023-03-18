package ru.tinkoff.web.controller;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.web.dto.AddLinkRequest;
import ru.tinkoff.web.dto.ListLinkResponse;
import ru.tinkoff.web.dto.RemoteLinkResponse;

@RestControllerAdvice
public class LinksController {

    @GetMapping("/links")
    public ListLinkResponse getLinks(@RequestParam("Tg-Chat-Id") long idChat) {
        return null;
    }

    @PostMapping("links")
    public AddLinkRequest addLink(@RequestParam("Tg-Chat-Id") long idChat, @RequestBody String addUrl) {
        return null;
    }

    @DeleteMapping("links")
    public RemoteLinkResponse deleteLink(@RequestParam("Tg-Chat-Id") long idChat, @RequestBody String deleteUrl) {
        return null;
    }
}
