package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.ServerCommand;

import java.util.Map;

public class ConstructorsManager implements CommandConstructor{
    Map<String, CommandConstructor> constructors;

    public ConstructorsManager(CommandConstructor ... constructors) {
        for (CommandConstructor constructor : constructors) {
            this.constructors.put(constructor.getName(), constructor);
        }
    }

    @Override
    public Command getCommand(String[] command, TextIO io) throws IllegalArgumentException {
        if(command.length == 0) throw new IllegalArgumentException("команда пустая");
        try {
            return constructors.get(command[0]).getCommand(command, io);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("команда " + command[0] + " не найдена");
        }
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
