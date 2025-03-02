package com.itmo.prog_lab5_8.commands;

public interface Command {
    public void execute(String command);
    public String getName();
    public String getDescription();
    public String getSyntax();
}
