package com.itmo.prog_lab5_8.cli.io;

public interface TextInput {
    boolean ready();
    String input(String prompt);
    String input();
}
