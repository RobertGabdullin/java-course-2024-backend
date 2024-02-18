package edu.java.bot.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import edu.java.bot.service.processor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import edu.java.bot.configuration.ApplicationConfig;
import java.util.List;

@Configuration
public class MyBot implements Bot {
    private final UserMessageProcessor messageProcessor;
    TelegramBot bot;
    @Autowired
    public MyBot(ApplicationConfig applicationConfig) {
        messageProcessor = new UserMessageProcessorImpl();
        bot = new TelegramBot(applicationConfig.telegramToken());
        bot.setUpdatesListener(this);
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
