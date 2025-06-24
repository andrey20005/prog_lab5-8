package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;

public class Info extends AbstractStandardCommand {
    private final Account account;

    public Info(Account account) {
        this.account = account;
    }
    @Override
    public void execute(Invoker invoker) {
        try {
            result = invoker.info(account);
        } catch (IncorrectRequestException e) {
            result = e.getMessage();
        }
    }
}
