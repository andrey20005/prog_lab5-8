package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.collection.Dragons;

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
    public void execute(String command, TextIO textIO) {
        textIO.println(dragons.getInfo());
    }
}
