package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.service.models.StateUser;
import ru.tinkoff.edu.java.bot.web.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.web.dto.ListLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.ListLinkResponse;

import java.net.URI;
import java.util.List;

public class CommandList implements Command {

    private ScrapperClient client;
    public CommandList(ru.tinkoff.edu.java.bot.web.client.ScrapperClient client) {
        this.client = client;
    }

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "Get lists tracked urls";
    }

    @Override
    public SendMessage handle(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        Mono<ListLinkResponse> response = client.listTrackedLink(new ListLinkRequest(update.message().chat().id()));
        List<URI> urls = response.block().urls();
        if (urls.size() > 0) {
            for (URI url : urls) {
                stringBuilder.append(url.toString()).append("\n");
            }
        } else {
            stringBuilder.append("No tracked links");
        }
        return new SendMessage(update.message().chat().id(), stringBuilder.toString());
    }

    @Override
    public StateUser getState() {
        return StateUser.NONE;
    }
}
