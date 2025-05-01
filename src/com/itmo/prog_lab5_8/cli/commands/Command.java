package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;

public interface Command {
    void execute(String command, TextIO textIO);
    String getName();
    String getDescription();
}
