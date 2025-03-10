package com.itmo.prog_lab5_8.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsManager {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    private final Pattern commandNamePattern = Pattern.compile("^(\\w+)( +|$)");
    public void execute(String command) throws IncorrectCommandException {
        Matcher commandNameMatcher = commandNamePattern.matcher(command);
        if (commandNameMatcher.find()) {
            String commandName = commandNameMatcher.group(1);
            if (commands.containsKey(commandName)) {
                commands.get(commandName).execute(command);
            } else {
                throw new IncorrectCommandException(command, "Комманда " + commandName + " не найдена.");
            }
        } else {
            throw new IncorrectCommandException(command, "Комманд должна выглядеть так:  имя_комманды [аргументы_комманды]");
        }
    }
}
