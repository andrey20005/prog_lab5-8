package com.itmo.prog_lab5_8.cli.io;

public interface TextInput {
    public boolean ready();
    public String input(String prompt);
    public String input();
}
