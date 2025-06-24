package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.commands.Clear;
import com.itmo.prog_lab5_8.common.commands.Command;

public class ClearConstructor extends AbstractCommandConstructor {
    private final Account account;

    public ClearConstructor(Account account) {
        super("clear", "clear: очистить коллекцию");
        this.account = account;
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        return new Clear(account);
    }
}
