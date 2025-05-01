package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.models.*;
import com.itmo.prog_lab5_8.cli.utils.Asker;
import com.itmo.prog_lab5_8.collection.Dragons;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
    public void execute(String command, TextIO textIO) {
        Scanner scanner = new Scanner(command.substring(getName().length() + 1));
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
            float coordinateX = asker.getFloat("координата x: ");
            float coordinateY = asker.getFloat("координата y: ");
            int age = asker.getPositiveInt("возраст дракона: ");
            String description = asker.getString("описание дракона: ");
            float weight = asker.getPositiveFloat("вес дракона: ");
            DragonCharacter character = asker.ask(Arrays.toString(DragonCharacter.values()) + "\n" + "характер дракона: ", DragonCharacter::valueOf);
            boolean haveKiller = asker.yesNo("есть ли убийца[yes|no]: ");
            if (!haveKiller) dragons.update(id, name, coordinateX, coordinateY, age, description, weight, character);
            else {
                dragons.update(
                        id,
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

    }
}
