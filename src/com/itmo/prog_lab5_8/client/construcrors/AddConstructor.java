package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Command;

public class AddConstructor extends AbstractCommandConstructor {
    public AddConstructor() {
        super("add", "add {element}: добавить новый элемент в коллекцию");
    }

    @Override
    public Command getCommand(String[] command, TextIO io) {
        return null;
    }
}
