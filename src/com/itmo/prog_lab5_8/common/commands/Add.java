package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.Dragon;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class Add extends AbstractStandardCommand {
    private Dragon dragon;

    public Add(Dragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.add(dragon);
            result = "в коллекцию успешно добавлен новый дракон";
        } catch (IllegalArgumentException e) {
            result = "переданные аргументы не корректны: \n" + e.getMessage();
        }
    }
}
