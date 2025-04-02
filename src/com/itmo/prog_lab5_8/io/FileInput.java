package com.itmo.prog_lab5_8.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInput implements TextInput{
    private String fileName;
    private FileReader reader;

    public FileInput(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        reader = new FileReader(fileName);
    }

    @Override
    public boolean ready() {
        try {
            return reader.ready();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String input() throws IOException {
        return new BufferedReader(reader).readLine();
    }

    @Override
    public String input(String prompt) throws IOException {
        return input();
    }
}
