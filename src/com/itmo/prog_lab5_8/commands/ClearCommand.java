package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.сollection.Dragons;

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
    public String getSyntax() {
        return "clear";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        dragons.clear();
    }
}
