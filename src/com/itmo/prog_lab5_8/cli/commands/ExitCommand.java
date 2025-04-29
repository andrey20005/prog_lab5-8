package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;

public class ExitCommand implements Command{
    public boolean running = true;
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit: завершить программу (без сохранения в файл)";
    }

    @Override
    public String getSyntax() {
        return "exit";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        running = false;
//        System.exit(0);
    }
}
