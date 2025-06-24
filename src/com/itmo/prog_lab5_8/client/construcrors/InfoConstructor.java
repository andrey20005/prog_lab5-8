package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Info;

public class InfoConstructor extends AbstractCommandConstructor {
    private final Account account;

    public InfoConstructor(Account account) {
        super("info", "info: информация о коллекции");
        this.account = account;
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        if (input.length != 1) throw new IllegalArgumentException("info не принимает аргументов");
        return new Info(account);
    }
}
