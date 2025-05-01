package com.itmo.prog_lab5_8.cli;

import com.itmo.prog_lab5_8.cli.commands.Command;
import com.itmo.prog_lab5_8.cli.io.TextIO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsManager {
    private final Map<String, Command> commands = new HashMap<>();
    private boolean running = true;

    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    private final Pattern commandNamePattern = Pattern.compile("(?U)^(\\w+)( +|$)");
    public void execute(String command, TextIO textIO) {
        Matcher commandNameMatcher = commandNamePattern.matcher(command);
        if (commandNameMatcher.find()) {
            String commandName = commandNameMatcher.group(1);
            if (commands.containsKey(commandName)) {
                commands.get(commandName).execute(command, textIO);
            } else {
                throw new IllegalArgumentException("Команда " + commandName + " не найдена.");
            }
        } else {
            throw new IllegalArgumentException("Команд должна выглядеть так:  имя_команды [аргументы_команды]");
        }
    }

    public boolean isRunning() {return running;}

    public void setRunning(boolean running) {this.running = running;}

    public String getHelp() {
        StringBuilder builder = new StringBuilder();
        commands.forEach((key, com) -> builder.append(com.getDescription()).append("\n"));
        return builder.toString();
    }
}
