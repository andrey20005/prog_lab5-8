package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.utils.DragonsXmlConverter;
import com.itmo.prog_lab5_8.utils.io.TextIO;
import com.itmo.prog_lab5_8.сollection.Dragons;

import javax.xml.bind.JAXBException;

public class ShowCommand implements Command {
    private Dragons dragons;
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
    public String getSyntax() {
        return "show";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        dragons.getDragons().forEach(
                dragon -> textIO.println(dragon.toString())
        );
    }
}
