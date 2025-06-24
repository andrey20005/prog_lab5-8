package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.Asker;
import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Update;
import com.itmo.prog_lab5_8.common.models.Dragon;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class UpdateConstructor extends AbstractCommandConstructor {
    private final Account account;

    public UpdateConstructor(Account account) {
        super("update", "update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        this.account = account;
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        if(input.length > 2) throw new IllegalArgumentException("присутствуют лишние аргументы");
        if(input.length < 2) throw new IllegalArgumentException("нехватает аргументов: id");
        Asker asker = new Asker(io);
        long id = Integer.parseInt(input[1]);
        Dragon dragon = asker.getDragon();
        dragon.setId(id);
        return new Update(dragon, account);
    }
}
