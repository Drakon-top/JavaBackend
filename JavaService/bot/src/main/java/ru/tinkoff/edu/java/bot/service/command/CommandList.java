package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.service.models.StateUser;

public class CommandList implements Command {
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
        // go to DB
        return new SendMessage(update.message().chat().id(), stringBuilder.toString());
    }

    @Override
    public StateUser getState() {
        return StateUser.NONE;
    }
}
