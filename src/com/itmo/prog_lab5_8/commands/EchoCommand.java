package com.itmo.prog_lab5_8.commands;

public class EchoCommand implements Command {
    @Override
    public void execute(String command) {
        if (command.length() > getName().length() + 1) {
            System.out.println(command.substring(getName().length() + 1));
        }
    }

    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public String getDescription() {
        return "Эхо";
    }

    @Override
    public String getSyntax() {
        return "echo [text]";
    }
}
