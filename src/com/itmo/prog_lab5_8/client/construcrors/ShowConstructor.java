package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Show;

public class ShowConstructor extends AbstractCommandConstructor {
    public ShowConstructor() {
        super("show", "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        return new Show();
    }
}
