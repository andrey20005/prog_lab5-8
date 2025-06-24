package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;

public class Show extends AbstractStandardCommand {
    private final Account account;

    public Show(Account account) {
        this.account = account;
    }
    @Override
    public void execute(Invoker invoker) {
        try {
            result = invoker.show(account);
        } catch (IncorrectRequestException e) {
            result = e.getMessage();
        }
    }
}
