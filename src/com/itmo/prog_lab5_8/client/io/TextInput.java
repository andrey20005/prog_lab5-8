package com.itmo.prog_lab5_8.client.io;

public interface TextInput {
    boolean ready();
    String input(String prompt);
    String input();
}
