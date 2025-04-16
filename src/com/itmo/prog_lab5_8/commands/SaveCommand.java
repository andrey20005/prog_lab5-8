package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.utils.DragonsXmlConverter;
import com.itmo.prog_lab5_8.сollection.Dragons;

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
    public String getSyntax() {
        return "save";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        DragonsXmlConverter.toXMLFile(dragons);
    }
}
