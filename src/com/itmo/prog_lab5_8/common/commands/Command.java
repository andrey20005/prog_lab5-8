package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;

import java.io.*;
import java.nio.ByteBuffer;

public interface Command extends Serializable {
    void execute(Invoker invoker);
    String getResult();
}
