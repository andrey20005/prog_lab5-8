package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.models.*;
import com.itmo.prog_lab5_8.cli.utils.Asker;
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
        return "add {name, coordinates.x, coordinates.y, age, description, weight, character, killer yes|no Null|{name, height, eyeColor, hairColor, location.x, location.y, location.name}}";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        try {
            Asker asker = new Asker(textIO);
            String name = asker.getWord("имя дракона: ");
            float coordinateX = asker.getFloat("координата x: ");
            float coordinateY = asker.getFloat("координата y: ");
            int age = asker.getPositiveInt("возраст дракона: ");
            String description = asker.getString("описание дракона: ");
            float weight = asker.getPositiveFloat("вес дракона: ");
            DragonCharacter character = asker.ask(Arrays.toString(DragonCharacter.values()) + "\n" + "характер дракона: ", DragonCharacter::valueOf);
            boolean haveKiller = asker.yesNo("есть ли убийца[yes|no]: ");
            if (!haveKiller) dragons.add(name, coordinateX, coordinateY, age, description, weight, character);
            else {
                dragons.add(
                        name,
                        coordinateX,
                        coordinateY,
                        age,
                        description,
                        weight,
                        character,
                        asker.getWord("имя убийцы: "),
                        asker.getPositiveDouble("рост убийцы: "),
                        asker.ask(Arrays.toString(Color.values()) + "\n" + "цвет глаз: ", Color::valueOf),
                        asker.ask(Arrays.toString(Color.values()) + "\n" + "цвет волос: ", Color::valueOf),
                        asker.getInt("координата Х убийцы: "),
                        asker.getInt("координата У убийцы: "),
                        asker.getWord("названия моста убийцы: ")
                        );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            Dragon newDragon = new Dragon();
//            newDragon.setName(Asker.ask("name: ", textIO, Asker::isWord));
//            newDragon.setCoordinates(new Coordinates(
//                    Float.parseFloat(Asker.ask("coordinate.x: ", textIO, Asker::isDecimal)),
//                    Float.parseFloat(Asker.ask("coordinate.y: ", textIO, Asker::isDecimal))
//            ));
//            newDragon.setAge(Integer.parseInt(Asker.ask("age: ", textIO, Asker::isNaturalNumber)));
//            newDragon.setDescription(Asker.ask("description: ", textIO, Asker::anything));
//            newDragon.setWeight(Float.parseFloat(Asker.ask("weight: ", textIO, Asker::isPositiveDecimal)));
//            newDragon.setCharacter(DragonCharacter.valueOf(Asker.ask(Arrays.toString(
//                    DragonCharacter.values()) + "\n" + "character: ",
//                    textIO,
//                    Asker.isConverted(DragonCharacter::valueOf
//                    ))));
//            if (Asker.yesNo("есть ли убийца: ", textIO)) {
//                Person killer = new Person();
//                killer.setName(Asker.ask("killer.name: ", textIO, Asker::isWord));
//                killer.setHeight(Double.parseDouble(Asker.ask("killer.height: ", textIO, Asker::isPositiveDecimal)));
//                killer.setEyeColor(Color.valueOf(Asker.ask(Arrays.toString(Color.values()) + "\nkiller.eyeColor: ", textIO, Asker.isConverted(Color::valueOf))));
//                killer.setHairColor(Color.valueOf(Asker.ask(Arrays.toString(Color.values()) + "\nkiller.hairColor: ", textIO, Asker.isConverted(Color::valueOf))));
//                killer.setLocation(new Location(
//                        Integer.parseInt(Asker.ask("killer.location.x: ", textIO, Asker::isNumber)),
//                        Integer.parseInt(Asker.ask("killer.location.y: ", textIO, Asker::isNumber)),
//                        Asker.ask("killer.location.name: ", textIO, Asker::isWord)
//                ));
//                newDragon.setKiller(killer);
//            }
//            newDragon.setCreationDate(ZonedDateTime.now());
//            dragons.add(newDragon);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (IncorrectInputException e) {
//            textIO.println("Полученный дракон некорректен" + "\n" + e.getMessage());
//        }
    }
}
