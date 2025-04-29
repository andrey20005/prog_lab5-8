package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.cli.utils.Asker;
import com.itmo.prog_lab5_8.сollection.Dragons;
import com.itmo.prog_lab5_8.сollection.IncorrectInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveByIdCommand implements Command {
    Dragons dragons;
    public RemoveByIdCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id id: удалить элемент из коллекции по его id";
    }

    @Override
    public String getSyntax() {
        return "remove_by_id id";
    }

    private final Pattern commandNamePattern = Pattern.compile("(?U)^(\\w+) +(\\w+) *$");
    @Override
    public void execute(String command, TextIO textIO) {
        Matcher commandMatcher = commandNamePattern.matcher(command);
        if(!commandMatcher.find()) textIO.println("команда должна содержать только id дракона");
        else if (!Asker.isPositiveDecimal(commandMatcher.group(2))) textIO.println("id дракона должен быть числом");
        else {
            long id = Long.parseLong(commandMatcher.group(2));
            try {
                dragons.removeById(id);
            } catch (IncorrectInputException e) {
                textIO.println("такого id нет");
            }
        }
    }
}
