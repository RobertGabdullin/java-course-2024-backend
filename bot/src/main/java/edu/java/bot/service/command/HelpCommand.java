package edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.processor.UserMessageProcessorImpl;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {

    private static final String ANSWER = """
            This bot helps you track resource changes on GitHub and StackOverFlow.

            You can control bot by sending these commands:
            """;

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
        UserMessageProcessorImpl userMessageProcessor = new UserMessageProcessorImpl();
        List<? extends Command> commands = userMessageProcessor.commands();
        StringBuilder description = new StringBuilder(ANSWER);
        for (Command command : commands) {
            String temp = command.command() + " - " + command.description() + '\n';
            description.append(temp);
        }
        return new SendMessage(update.message().chat().id(), description.toString());
    }
}
