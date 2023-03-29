package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.service.models.StateUser;
import ru.tinkoff.edu.java.bot.web.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.web.dto.AddLinkResponse;

@Component
public class CommandTrack implements Command {

    private ScrapperClient client;
    public CommandTrack(ScrapperClient client) {
        this.client = client;
    }
    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Add track url";
    }

    @Override
    public SendMessage handle(Update update) {
        // User user = getDB
        // user.state = Track
        return new SendMessage(update.message().chat().id(), "Enter url for track");
    }

    @Override
    public StateUser getState() {
        return StateUser.TRACK;
    }

    @Override
    public SendMessage handleWithArgument(Update update) {
        // Add url
        String url = update.message().text();
        Mono<AddLinkResponse> response = client.addTrackedLink(new AddLinkRequest(update.message().chat().id(), url));
//        System.out.println(response.log() + " result");
        return new SendMessage(update.message().chat().id(), "Add url for track");
    }
}
