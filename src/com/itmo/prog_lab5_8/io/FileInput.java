package com.itmo.prog_lab5_8.io;

import java.io.*;
import java.util.Scanner;

public class FileInput implements TextInput{
//    private String fileName;
//    private FileReader reader;
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
    public String input() throws IOException {
        String text = scanner.nextLine();
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   " + text);
        return text;
    }

    @Override
    public String input(String prompt) throws IOException {
        return input();
    }
}
