package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invokers;

public class Clear extends Command {
    @Override
    public void execute(Invokers invokers) {
        try {
            invokers.cm.clear();
            result = "коллекция очищена";
        } catch (RuntimeException e) {
            result = "во время исполнения команды произошла ошибка:\n" + e.getMessage();
        }
    }
}
