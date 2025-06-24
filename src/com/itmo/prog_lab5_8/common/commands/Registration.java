package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;

public class Registration extends AbstractStandardCommand {
    private final Account account;

    public Registration(Account account) {
        this.account = account;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.registration(account);
            result = "регистрация успешно завершена";
        } catch (IncorrectRequestException e) {
            result = e.getMessage();
        }
    }
}
