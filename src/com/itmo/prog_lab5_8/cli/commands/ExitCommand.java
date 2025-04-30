package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.CommandsManager;
import com.itmo.prog_lab5_8.cli.io.TextIO;

public class ExitCommand implements Command{
    private final CommandsManager commands;
    public ExitCommand(CommandsManager commands) {
        this.commands = commands;
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit: завершить программу (без сохранения в файл)";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        commands.setRunning(false);
    }
}
