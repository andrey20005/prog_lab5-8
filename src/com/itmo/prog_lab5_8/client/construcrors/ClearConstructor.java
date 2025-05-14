package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Clear;
import com.itmo.prog_lab5_8.common.commands.Command;

public class ClearConstructor extends AbstractCommandConstructor {
    public ClearConstructor() {
        super("clear", "clear: очистить коллекцию");
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        return new Clear();
    }
}
