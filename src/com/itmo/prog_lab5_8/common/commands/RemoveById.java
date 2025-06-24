package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;

public class RemoveById extends AbstractStandardCommand {

    private final long id;
    private final Account account;

    public RemoveById(long id, Account account) {
        this.id = id;
        this.account = account;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.removeById(id, account);
            result = "элемент убран";
        } catch (IncorrectRequestException e) {
            result = e.getMessage();
        }
    }
}
