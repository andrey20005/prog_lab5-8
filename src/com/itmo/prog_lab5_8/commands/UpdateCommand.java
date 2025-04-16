package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.models.*;
import com.itmo.prog_lab5_8.utils.Asker;
import com.itmo.prog_lab5_8.сollection.Dragons;
import com.itmo.prog_lab5_8.сollection.IncorrectInputException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCommand implements Command{
    Dragons dragons;
    public UpdateCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element}: обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getSyntax() {
        return "update id {name, coordinates.x, coordinates.y, age, description, weight, character, killer yes|no Null|{name, height, eyeColor, hairColor, location.x, location.y, location.name}}";
    }

    private final Pattern commandNamePattern = Pattern.compile("(?U)^(\\w+) +(\\w+) *$");
    @Override
    public void execute(String command, TextIO textIO) {
        Matcher commandMatcher = commandNamePattern.matcher(command);
        if(!commandMatcher.find()) textIO.println("команда должна содержать только id дракона");
        else if (!Asker.isPositiveDecimal(commandMatcher.group(2))) textIO.println("id дракона должен быть числом");
        else {
            long id = Long.parseLong(commandMatcher.group(2));
            if (!dragons.hasDragonId(id)) textIO.println("дракон с таким id не найден");
            else {
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
                    dragons.update(id, newDragon);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (IncorrectInputException e) {
                    textIO.println("дракон неправильный" + "\n" + e.getMessage());
                }
            }
        }
    }
}
