package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.CommandsManager;
import com.itmo.prog_lab5_8.cli.io.TextIO;

public class HelpCommand implements Command{
    private CommandsManager commandsManager;

    public HelpCommand(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help: вывести справку по доступным командам";
    }

    @Override
    public String getSyntax() {
        return "help";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        commandsManager.getCommands().forEach(
                (key, com) -> textIO.println(com.getDescription())
        );
    }
}
