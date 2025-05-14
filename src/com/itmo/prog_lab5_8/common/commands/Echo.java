package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;

public class Echo extends AbstractStandardCommand{
    private final String text;

    public Echo(String text) {
        this.text = text;
    }

    @Override
    public void execute(Invoker invoker) {
        result = invoker.echo(text);
    }
}
