package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.collection.Dragons;

public class ClearCommand implements Command {
    Dragons dragons;
    public ClearCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear: очистить коллекцию";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        dragons.clear();
    }
}
