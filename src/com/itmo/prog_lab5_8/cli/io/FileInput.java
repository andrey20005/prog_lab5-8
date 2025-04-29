package com.itmo.prog_lab5_8.cli.io;

import java.io.*;
import java.util.Scanner;

public class FileInput implements TextInput{
    private Scanner scanner;

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
        String text = scanner.nextLine();
        return text;
    }

    @Override
    public String input(String prompt) {
        return input();
    }
}
