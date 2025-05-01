package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.collection.Dragons;

public class SaveCommand implements Command {
    Dragons dragons;
    public SaveCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save: сохранить коллекцию";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        dragons.save();
    }
}
