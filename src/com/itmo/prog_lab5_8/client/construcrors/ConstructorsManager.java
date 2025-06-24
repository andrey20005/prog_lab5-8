package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class ConstructorsManager implements CommandConstructor{
    Map<String, CommandConstructor> constructors = new HashMap<>();

    public ConstructorsManager(CommandConstructor ... constructors) {
        for (CommandConstructor constructor : constructors) {
            this.constructors.put(constructor.getName(), constructor);
        }
    }

    public String getHelp() {
        StringBuilder help = new StringBuilder();
        constructors.forEach((name, constructor) -> help.append(constructor.getDescription() + "\n"));
        return help.toString();
    }

    public void add(CommandConstructor constructor) {
        this.constructors.put(constructor.getName(), constructor);
    }

    @Override
    public Command getCommand(String[] input, TextIO io) throws IncorrectRequestException {
        if(input.length == 0) throw new IllegalArgumentException("запрос пустой");
        try {
            return constructors.get(input[0]).getCommand(input, io);
        } catch (NullPointerException e) {
            throw new IncorrectRequestException("команда " + input[0] + " не найдена");
        }
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        StringBuilder help = new StringBuilder();
        constructors.forEach((name, constructor) -> help.append(constructor.getDescription() + "\n"));
        return help.toString();
    }
}
