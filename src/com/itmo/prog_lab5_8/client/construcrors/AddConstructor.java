package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.Asker;
import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Add;
import com.itmo.prog_lab5_8.common.commands.Command;

public class AddConstructor extends AbstractCommandConstructor {
    public AddConstructor() {
        super("add", "add {element}: добавить новый элемент в коллекцию");
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        if(input.length > 1) throw new IllegalArgumentException("присутствуют лишние аргументы");
        Asker asker = new Asker(io);
        return new Add(asker.getDragon());
    }
}
