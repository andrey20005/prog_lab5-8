package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.сollection.Dragons;

public class InfoCommand implements Command {
    Dragons dragons;
    public InfoCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info: вывести в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public String getSyntax() {
        return "info";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        textIO.println("время создания: " + dragons.getCreationTime().toString());
        textIO.println("количество элементов: " + dragons.getLength());
    }
}
