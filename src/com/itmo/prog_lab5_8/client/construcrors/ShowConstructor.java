package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Show;

public class ShowConstructor extends AbstractCommandConstructor {
    private final Account account;

    public ShowConstructor(Account account) {
        super("show", "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.account = account;
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        return new Show(account);
    }
}
