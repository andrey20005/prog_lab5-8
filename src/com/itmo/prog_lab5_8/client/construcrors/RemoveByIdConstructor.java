package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.RemoveById;

public class RemoveByIdConstructor extends AbstractCommandConstructor {
    private final Account account;

    public RemoveByIdConstructor(Account account) {
        super("remove_by_id", "remove_by_id id: удалить элемент из коллекции по его id");
        this.account = account;
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        if(input.length > 2) throw new IllegalArgumentException("присутствуют лишние аргументы");
        if(input.length < 2) throw new IllegalArgumentException("нехватает аргументов: id");
        try {
            return new RemoveById(Integer.parseInt(input[1]), account);
        } catch (NumberFormatException e) {
            throw new RuntimeException("id должно быть целым числом");
        }
    }
}
