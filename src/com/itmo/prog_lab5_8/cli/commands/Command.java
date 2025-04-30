package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;

public interface Command {
    public void execute(String command, TextIO textIO);
    public String getName();
    public String getDescription();
}
