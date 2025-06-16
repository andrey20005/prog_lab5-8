package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;

public class CheckId extends AbstractStandardCommand{
    private final long id;

    public CheckId(long id) {
        this.id = id;
    }

    @Override
    public void execute(Invoker invoker) {
        result = Boolean.toString(invoker.checkId(id));
    }
}
