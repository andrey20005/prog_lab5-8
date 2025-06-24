package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Dragon;

public class Update extends AbstractStandardCommand {
    private final Dragon dragon;
    private final Account account;

    public Update(Dragon dragon, Account account) {
        this.dragon = dragon;
        this.account = account;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.update(dragon, account);
            result = "дракон обновлен";
        } catch (IncorrectRequestException e) {
            result = e.getMessage();
        }
    }
}
