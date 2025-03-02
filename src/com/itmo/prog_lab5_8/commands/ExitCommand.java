package com.itmo.prog_lab5_8.commands;

public class ExitCommand implements Command{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "`exit: завершить программу (без сохранения в файл)";
    }

    @Override
    public String getSyntax() {
        return "exit";
    }

    @Override
    public void execute(String command) {
        System.exit(0);
    }
}
