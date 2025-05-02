package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invokers;

public class Show extends Command {
    @Override
    public void execute(Invokers invokers) {
        result = invokers.cm.show();
    }
}
