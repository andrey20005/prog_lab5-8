package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.models.Coordinates;
import com.itmo.prog_lab5_8.models.Dragon;
import com.itmo.prog_lab5_8.models.DragonCharacter;
import com.itmo.prog_lab5_8.utils.Asker;
import com.itmo.prog_lab5_8.сollection.Dragons;

import java.io.IOException;
import java.util.Arrays;

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
            Dragon newDragon = new Dragon();
            newDragon.setName(Asker.ask("name: ", textIO, Asker::isWord));
            newDragon.setCoordinates(new Coordinates(
                    Float.parseFloat(Asker.ask("coordinate.x: ", textIO, Asker::isDecimal)),
                    Float.parseFloat(Asker.ask("coordinate.y: ", textIO, Asker::isDecimal))
            ));
            newDragon.setAge(Integer.parseInt(Asker.ask("age: ", textIO, Asker::isNaturalNumber)));
            newDragon.setDescription(Asker.ask("description: ", textIO, Asker::anything));
            newDragon.setWeight(Float.parseFloat(Asker.ask("weight: ", textIO, Asker::isPositiveDecimal)));
            newDragon.setCharacter(DragonCharacter.valueOf(Asker.ask(Arrays.toString(
                    DragonCharacter.values()) + "\n" + "character: ",
                    textIO,
                    Asker.isConverted(DragonCharacter::valueOf
                    ))));
            if (Asker.yesNo("есть ли убийца: ", textIO)) {

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
