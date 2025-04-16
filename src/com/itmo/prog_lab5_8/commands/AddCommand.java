package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.models.*;
import com.itmo.prog_lab5_8.utils.Asker;
import com.itmo.prog_lab5_8.сollection.Dragons;
import com.itmo.prog_lab5_8.сollection.IncorrectInputException;

import java.io.IOException;
import java.time.ZonedDateTime;
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
        return "add {name, coordinates.x, coordinates.y, age, description, weight, character, killer yes|no Null|{name, height, eyeColor, hairColor, location.x, location.y, location.name}}";
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
                Person killer = new Person();
                killer.setName(Asker.ask("killer.name: ", textIO, Asker::isWord));
                killer.setHeight(Double.parseDouble(Asker.ask("killer.height: ", textIO, Asker::isPositiveDecimal)));
                killer.setEyeColor(Color.valueOf(Asker.ask(Arrays.toString(Color.values()) + "\nkiller.eyeColor: ", textIO, Asker.isConverted(Color::valueOf))));
                killer.setHairColor(Color.valueOf(Asker.ask(Arrays.toString(Color.values()) + "\nkiller.hairColor: ", textIO, Asker.isConverted(Color::valueOf))));
                killer.setLocation(new Location(
                        Integer.parseInt(Asker.ask("killer.location.x: ", textIO, Asker::isNumber)),
                        Integer.parseInt(Asker.ask("killer.location.y: ", textIO, Asker::isNumber)),
                        Asker.ask("killer.location.name: ", textIO, Asker::isWord)
                ));
                newDragon.setKiller(killer);
            }
            newDragon.setCreationDate(ZonedDateTime.now());
            dragons.add(newDragon);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IncorrectInputException e) {
            textIO.println("Полученный дракон некорректен" + "\n" + e.getMessage());
        }
    }
}
