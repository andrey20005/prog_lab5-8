package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;

public class Show extends AbstractStandardCommand {
    @Override
    public void execute(Invoker invoker) {
        result = invoker.show();
    }
}
