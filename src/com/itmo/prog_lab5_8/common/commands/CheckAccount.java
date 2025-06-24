package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;

public class CheckAccount extends AbstractStandardCommand{
    private final Account account;
    public boolean isAccountCorrect;

    public CheckAccount(Account account) {
        this.account = account;
        isAccountCorrect = false;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            isAccountCorrect = invoker.checkAccount(account);
            result = "такой аккаунт существует";
        } catch (IncorrectRequestException e) {
            result = e.getMessage();
        }
    }
}
