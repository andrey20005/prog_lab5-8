package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Command;

public interface CommandConstructor {
    Command getCommand(String[] input, TextIO io);
    String getName();
    String getDescription();
}
