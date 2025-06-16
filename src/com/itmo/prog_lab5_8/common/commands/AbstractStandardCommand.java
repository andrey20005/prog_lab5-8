package com.itmo.prog_lab5_8.common.commands;

public abstract class AbstractStandardCommand implements Command{
    String result;

    @Override
    public String getResult() {
        return result;
    }
}
