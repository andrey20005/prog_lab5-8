package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.Dragon;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class Update extends AbstractStandardCommand {
    private Dragon dragon;

    public Update(Dragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.update(dragon);
            result = "дракон обновлен";
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
        }
    }
}
