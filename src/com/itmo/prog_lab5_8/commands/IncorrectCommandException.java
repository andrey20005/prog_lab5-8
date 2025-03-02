package com.itmo.prog_lab5_8.commands;

public class IncorrectCommandException extends RuntimeException {
    public IncorrectCommandException(String command, String problemDescription) {
        super("Эта комманда не корректна: " + command + "\n" + problemDescription);
    }
}
