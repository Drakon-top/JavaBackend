package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.models.StateUser;

@Component
public class CommandStart implements Command {
    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Start of work with bot";
    }

    @Override
    public SendMessage handle(Update update) {
        // create chat id
        return new SendMessage(update.message().chat().id(), "Hello, " +
                update.message().from().firstName() + " " + update.message().from().lastName() +
                ". Enter /help to find out what I can");
    }

    @Override
    public StateUser getState() {
        return StateUser.NONE;
    }
}
