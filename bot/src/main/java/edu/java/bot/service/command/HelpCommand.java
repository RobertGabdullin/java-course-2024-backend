package edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.processor.UserMessageProcessorImpl;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class HelpCommand implements Command {
    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "show available commands";
    }

    @Override
    public SendMessage handle(Update update) {
        String ans = "This bot helps you track resource changes on GitHub and StackOverFlow.\n\n" +
            "You can control bot by sending these commands:\n";
        UserMessageProcessorImpl mesProcessor = new UserMessageProcessorImpl();
        List<? extends Command> commands = mesProcessor.commands();
        StringBuilder description = new StringBuilder(ans);
        for(Command command : commands){
            description.append(command.command() + " - " + command.description() + '\n');
        }
        return new SendMessage(update.message().chat().id(), description.toString());
    }
}
