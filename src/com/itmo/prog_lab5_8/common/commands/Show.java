package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invokers;

public class Show extends ServerCommand {
    @Override
    public void execute() {
        result = invokers.cm.show();
    }
}
