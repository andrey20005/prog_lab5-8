package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.cli.utils.Asker;

import java.io.IOException;

public class TestInputCommand implements Command {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "просто для проверки во время разработки, нужно убрать";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public void execute(String command, TextIO textIO) {
        Asker asker = new Asker(textIO);
        try {
            textIO.println(asker.getWord("слово: "));
            textIO.println(String.valueOf(asker.getPositiveInt("положительное число: ")));
            textIO.println(String.valueOf(asker.getInt("число: ")));
            textIO.println(String.valueOf(asker.yesNo("да нет: ")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
