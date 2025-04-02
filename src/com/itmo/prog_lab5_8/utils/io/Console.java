package com.itmo.prog_lab5_8.utils.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console implements TextIO {
    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public boolean ready() {
        return true;
    }

    @Override
    public String input() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    @Override
    public String input(String prompt) throws IOException {
        System.out.print(prompt);
        return input();
    }
}
