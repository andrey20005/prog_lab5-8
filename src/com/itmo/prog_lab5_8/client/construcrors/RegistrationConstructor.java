package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Registration;

public class RegistrationConstructor extends AbstractCommandConstructor {

    public RegistrationConstructor() {
        super("reg", "reg login password: команда для регистрации");
    }

    @Override
    public Command getCommand(String[] input, TextIO io) throws IncorrectRequestException {
        if (input.length != 3) throw new IncorrectRequestException("команда принимает два аргумента");
        return new Registration(new Account(input[1], input[2]));
    }
}
