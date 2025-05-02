package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invokers;

public class RemoveById extends ServerCommand {

    private final long id;

    public RemoveById(long id) {
        this.id = id;
    }

    @Override
    public void execute() {
        try {
            invokers.cm.removeById(id);
            result = "коллекция очищена";
        } catch (IllegalArgumentException e) {
            result = "не найдено дракона с таким id";
        } catch (RuntimeException e) {
            result = "во время исполнения команды произошла ошибка:\n" + e.getMessage();
        }
    }
}
