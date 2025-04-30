package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;

public class EchoCommand implements Command {
    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public String getDescription() {
        return "echo: копирует введенный текст";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        if (command.length() > getName().length() + 1) {
            textIO.println(command.substring(getName().length() + 1));
        }
    }
}
