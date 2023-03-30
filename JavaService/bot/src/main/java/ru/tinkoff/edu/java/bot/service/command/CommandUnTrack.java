package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.service.models.StateUser;
import ru.tinkoff.edu.java.bot.web.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkResponse;
import ru.tinkoff.edu.java.bot.web.dto.DeleteLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.DeleteLinkResponse;

@Component
public class CommandUnTrack implements Command {
    private ScrapperClient client;
    public CommandUnTrack(ScrapperClient client) {
        this.client = client;
    }
    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "Delete tracked url";
    }

    @Override
    public SendMessage handle(Update update) {
        // User user = getDB
        // user.state = UnTrack
        return new SendMessage(update.message().chat().id(), "Enter url for untrack");
    }

    @Override
    public StateUser getState() {
        return StateUser.UNTRACK;
    }

    @Override
    public SendMessage handleWithArgument(Update update) {
        // Delete url
        String url = update.message().text();
        // Detective url
        Mono<DeleteLinkResponse> response = client.deleteTrackedLink(new DeleteLinkRequest(update.message().chat().id(), url));
        return new SendMessage(update.message().chat().id(), "Delete tracked url");
    }
}
