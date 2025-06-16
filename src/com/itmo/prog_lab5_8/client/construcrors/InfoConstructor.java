package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Info;

public class InfoConstructor extends AbstractCommandConstructor {
    public InfoConstructor() {
        super("info", "info: информация о коллекции");
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        if (input.length != 1) throw new IllegalArgumentException("info не принимает аргументов");
        return new Info();
    }
}
