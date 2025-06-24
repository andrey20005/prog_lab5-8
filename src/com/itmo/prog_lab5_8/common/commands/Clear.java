package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;

public class Clear extends AbstractStandardCommand {
    private final Account account;

    public Clear(Account account) {
        this.account = account;
    }
    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.clear(account);
            result = "коллекция очищена";
        } catch (IncorrectRequestException e) {
            result = "во время исполнения команды произошла ошибка:\n" + e.getMessage();
        }
    }
}
