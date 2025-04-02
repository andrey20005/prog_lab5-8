package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.TextIO;

public interface Command {
    public void execute(String command, TextIO textIO);
    public String getName();
    public String getDescription();
    public String getSyntax();
}
