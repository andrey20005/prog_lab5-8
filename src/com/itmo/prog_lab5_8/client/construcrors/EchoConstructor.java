package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Echo;

public class EchoConstructor extends AbstractCommandConstructor{
    public EchoConstructor() {
        super("echo", "echo: возвращает текст обратно");
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        if (input.length == 1) return new Echo("");
        return new Echo(String.join(" ", input).substring(5));
    }
}
