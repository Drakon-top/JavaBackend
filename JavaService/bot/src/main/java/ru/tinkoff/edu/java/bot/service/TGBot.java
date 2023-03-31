package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.configuration.BotConfiguration;
import ru.tinkoff.edu.java.bot.service.command.Command;
import ru.tinkoff.edu.java.bot.service.command.CommandTrack;
import ru.tinkoff.edu.java.bot.service.command.CommandUnTrack;
import ru.tinkoff.edu.java.bot.service.models.StateUser;
import ru.tinkoff.edu.java.bot.service.models.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TGBot implements Bot {
    private TelegramBot bot;
    private List<Command> commands;

    public TGBot(@Value("#{getBotToken}") String token, List<Command> commands) {
        this.commands = commands;
        bot = new TelegramBot(token);
        bot.setUpdatesListener(this);
        BotCommand[] botCommands = new BotCommand[commands.size()];
        for (int i = 0; i < commands.size(); i++) {
            Command command = commands.get(i);
            botCommands[i] = new BotCommand(command.command(), command.description());
        }
        bot.execute(new SetMyCommands(botCommands));
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
            System.out.println(update);
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
