package ru.tinkoff.edu.java.scrapper.web.controller;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.ListLinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoteLinkResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class LinksController {

    @GetMapping(path = "/links", produces = APPLICATION_JSON_VALUE)
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
