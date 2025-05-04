package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;

public class Clear extends AbstractStandardCommand {
    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.clear();
            result = "коллекция очищена";
        } catch (RuntimeException e) {
            result = "во время исполнения команды произошла ошибка:\n" + e.getMessage();
        }
    }
}
