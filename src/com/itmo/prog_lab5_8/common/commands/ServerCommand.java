package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invokers;

import java.io.Serializable;

public abstract class ServerCommand implements Command, Serializable {
    protected Invokers invokers;

    public void setInvokers(Invokers invokers) {
        this.invokers = invokers;
    }

    public abstract void execute();

    public final void execute(Invokers invokers) {
        setInvokers(invokers);
        execute();
    }

    String result;
    public String getResult() {return result;}
}
