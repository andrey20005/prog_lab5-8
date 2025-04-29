package com.itmo.prog_lab5_8.cli.io;

import java.util.Scanner;

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
    public String input() {
        return new Scanner(System.in).nextLine();
    }

    @Override
    public String input(String prompt) {
        System.out.print(prompt);
        return input();
    }
}
