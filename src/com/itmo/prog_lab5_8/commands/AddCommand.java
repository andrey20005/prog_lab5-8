package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.models.DragonCharacter;
import com.itmo.prog_lab5_8.utils.Asker;
import com.itmo.prog_lab5_8.сollection.Dragons;

import javax.imageio.IIOException;
import java.io.IOException;

public class AddCommand implements Command {
    Dragons dragons;
    public AddCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element}: добавить новый элемент в коллекцию";
    }

    @Override
    public String getSyntax() {
        return "add {element: name, coordinates, age, description, weight, character, killer}";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        try {
            String name = Asker.ask("name: ", textIO, Asker::isWord);
            double coordinateX = Double.parseDouble(Asker.ask("coordinate.x: ", textIO, Asker::isDouble));
            double coordinateY = Double.parseDouble(Asker.ask("coordinate.y: ", textIO, Asker::isDouble));
            long age = Long.parseLong(Asker.ask("age: ", textIO, Asker::isNumber));
            String description = Asker.ask("description: ", textIO, (t) -> true);
            double weight = Double.parseDouble(Asker.ask("weight: ", textIO, Asker::isDouble));
            DragonCharacter character = DragonCharacter.valueOf(Asker.ask(
                    "character: ",
                    textIO,
                    (text) -> Asker.isConverted(text, DragonCharacter::valueOf)
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
