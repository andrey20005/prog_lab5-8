package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;

public class RemoveById extends AbstractStandardCommand {

    private final long id;

    public RemoveById(long id) {
        this.id = id;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            invoker.removeById(id);
            result = "элемент убран";
        } catch (IllegalArgumentException e) {
            result = "не найдено дракона с таким id";
        } catch (RuntimeException e) {
            result = "во время исполнения команды произошла ошибка:\n" + e.getMessage();
        }
    }
}
