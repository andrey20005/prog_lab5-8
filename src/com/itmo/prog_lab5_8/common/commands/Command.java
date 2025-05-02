package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.CollectionManager;
import com.itmo.prog_lab5_8.common.Invokers;

import java.io.Serializable;

public abstract class Command implements Serializable {
    public abstract void execute(Invokers invokers);

    String result;
    public String getResult() {return result;}
}
