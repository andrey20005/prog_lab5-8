package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.collectors.FloatArgument;
import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.models.*;
import com.itmo.prog_lab5_8.cli.utils.Asker;
import com.itmo.prog_lab5_8.сollection.Dragons;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
        Scanner scanner = new Scanner(command.substring(getName().length()));
        long id;
        try {
            id = scanner.nextLong();
            if (id<=0) throw new IllegalArgumentException("индекс должен быть положительным");
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("первым аргументом должно быть число");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("нужно передать аргумент");
        }

        try {
            Asker asker = new Asker(textIO);
            String name = asker.getWord("имя дракона: ");
            float coordinateX = new FloatArgument("координата X: ", textIO).getArgument();
            float coordinateY = asker.getFloat("координата y: ");
            int age = asker.getPositiveInt("возраст дракона: ");
            String description = asker.getString("описание дракона: ");
            float weight = asker.getPositiveFloat("вес дракона: ");
            DragonCharacter character = asker.ask(Arrays.toString(DragonCharacter.values()) + "\n" + "характер дракона: ", DragonCharacter::valueOf);
            boolean haveKiller = asker.yesNo("есть ли убийца[yes|no]: ");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }
}
