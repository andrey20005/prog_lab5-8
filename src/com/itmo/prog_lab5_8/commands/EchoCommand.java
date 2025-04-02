package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;

public class EchoCommand implements Command {
    @Override
    public void execute(String command, TextIO textIO) {
        if (command.length() > getName().length() + 1) {
            textIO.println(command.substring(getName().length() + 1));
//            System.out.println(command.substring(getName().length() + 1));
        }
    }

    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public String getDescription() {
        return "echo: копирует введенный текст";
    }

    @Override
    public String getSyntax() {
        return "echo [text]";
    }
}
