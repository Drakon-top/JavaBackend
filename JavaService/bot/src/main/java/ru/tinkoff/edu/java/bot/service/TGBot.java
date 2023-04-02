package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.service.command.Command;
import ru.tinkoff.edu.java.bot.enums.StateUser;
import ru.tinkoff.edu.java.bot.service.models.User;


import java.util.List;

@Service
public class TGBot implements Bot {
    private TelegramBot bot;
    private List<Command> commands;

    public TGBot(@Value("#{getBotToken}") String token, List<Command> commands) {
        this.commands = commands;
        bot = new TelegramBot(token);
        bot.setUpdatesListener(this);
        bot.execute(new SetMyCommands(commands.stream().map(x -> new BotCommand(x.command(), x.description()))
                .toArray(BotCommand[]::new)));
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        if (request != null) {
            SendResponse sendResponse = (SendResponse) bot.execute(request);
            boolean ok = sendResponse.isOk();
            Message message = sendResponse.message();
        }
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            SendMessage request = null;
            if (update.message().entities() != null && update.message().entities()[0].type() == MessageEntity.Type.bot_command) {
                String comm = update.message().text();
                for (Command command : commands) {
                    if (command.command().equals(comm) ){
                        request = command.handle(update);
                        execute(request);
                        // user.state = command.getState();
                        break;
                    }
                }
                if (request == null) {
                    execute(new SendMessage(update.message().chat().id(), "Unidentified command"));
                }
            } else {
                User user = new User(update.message().chat().id(), StateUser.UNTRACK);
                if (user.state() != StateUser.NONE) {
                    for (Command command : commands) {
                        if (command.getState() == user.state()) {
                            request = command.handleWithArgument(update);
                            execute(request);
                            break;
                        }
                    }
                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
