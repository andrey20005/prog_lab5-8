package com.itmo.prog_lab5_8.cli.io;

import java.io.*;
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
        String text = scanner.nextLine();
        lastInput = text;
        return text;
    }

    @Override
    public String input(String prompt) {
        return input();
    }

    public String getLastInput() {return counter + " " +  lastInput;}
}
