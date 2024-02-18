package edu.java.bot.service.processor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.ArrayList;
import edu.java.bot.service.command.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserMessageProcessorImpl implements UserMessageProcessor {
    private final List<Command> commands;

    public UserMessageProcessorImpl() {
        // Initialize the list of commands
        commands = new ArrayList<>();
        commands.add(new StartCommand());
        commands.add(new HelpCommand());
        commands.add(new TrackCommand());
        commands.add(new UntrackCommand());
        commands.add(new ListCommand());
    }

    @Override
    public List<? extends Command> commands() {
        return commands;
    }

    @Override
    public SendMessage process(Update update) {
        for (Command command : commands) {
            if (command.supports(update)) {
                return command.handle(update);
            }
        }
        return new SendMessage(update.message().chat().id(), "Unknown command. Type /help for available commands.");
    }
}
