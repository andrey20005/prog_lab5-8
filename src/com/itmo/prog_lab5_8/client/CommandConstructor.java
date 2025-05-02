package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Command;

public interface CommandConstructor {
    Command getCommand(String command, TextIO io);
}
