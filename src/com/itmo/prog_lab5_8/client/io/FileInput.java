package com.itmo.prog_lab5_8.client.io;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileInput implements TextInput{
    private final Scanner scanner;
    private int counter = 0;
    private String lastInput;

    public FileInput(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(fileName);
        scanner = new Scanner(reader);
    }

    @Override
    public boolean ready() {
        return scanner.hasNext();
    }

    @Override
    public String input() {
        counter++;
        try {
            String text = scanner.nextLine();
            lastInput = text;
            return text;
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("файл обрывается");
        }
    }

    @Override
    public String input(String prompt) {
        return input();
    }

    public String getLastInput() {return counter + " " +  lastInput;}
}
