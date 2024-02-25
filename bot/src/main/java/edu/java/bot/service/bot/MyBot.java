package edu.java.bot.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.service.command.Command;
import edu.java.bot.service.processor.UserMessageProcessor;
import edu.java.bot.service.processor.UserMessageProcessorImpl;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBot implements Bot {
    private final UserMessageProcessor messageProcessor;
    private final TelegramBot bot;

    @Autowired
    public MyBot(ApplicationConfig applicationConfig) {
        messageProcessor = new UserMessageProcessorImpl();
        bot = new TelegramBot(applicationConfig.telegramToken());
        bot.setUpdatesListener(this);
        setMyCommands();
    }

    private void setMyCommands() {
        List<? extends Command> commands = messageProcessor.commands();
        BotCommand[] botCommands = commands.stream()
            .map(Command::toApiCommand)
            .toArray(BotCommand[]::new);
        bot.execute(new SetMyCommands(botCommands));
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        bot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            execute(messageProcessor.process(update));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void start() {
        // Implement bot startup logic
    }

    @Override
    public void close() {
        // Implement bot shutdown logic
    }
}
