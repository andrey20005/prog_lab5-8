package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.collection.Dragons;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RemoveByIdCommand implements Command {
    Dragons dragons;
    public RemoveByIdCommand(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id id: удалить элемент из коллекции по его id";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        long id;
        try {
            Scanner scanner = new Scanner(command.substring(getName().length() + 1));
            id = scanner.nextLong();
            if (id<=0) throw new IllegalArgumentException("индекс должен быть положительным");
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("первым аргументом должно быть число");
        } catch (NoSuchElementException | IllegalStateException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("нужно передать аргумент");
        }

        dragons.removeById(id);
    }
}
