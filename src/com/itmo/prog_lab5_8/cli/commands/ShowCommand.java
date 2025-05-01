package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.collection.Dragons;

public class ShowCommand implements Command {
    private final Dragons dragons;
    public ShowCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        textIO.println(dragons.toString());
    }
}
