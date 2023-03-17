package ru.tinkoff.web;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.web.dto.AddLinkRequest;
import ru.tinkoff.web.dto.ListLinkResponse;
import ru.tinkoff.web.dto.RemoteLinkResponse;

@RestControllerAdvice
public class WebController {

    @PostMapping("/tg-chat/{id}")
    public void registerChat(@PathVariable("id") long id) {
        return;
    }

    @DeleteMapping("/tg-chat/{id}")
    public void deleteChat(@PathVariable("id") long id) {
        return;
    }

    @GetMapping("/links")
    public ListLinkResponse getLinks(long id) {
        return null;
    }

    @PostMapping("links")
    public AddLinkRequest addLink(long id, String url) {
        return null;
    }

    @DeleteMapping("links")
    public RemoteLinkResponse deleteLink(long id, String url) {
        return null;
    }
}
