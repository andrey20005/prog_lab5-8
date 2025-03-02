package com.itmo.prog_lab5_8;

import com.itmo.prog_lab5_8.commands.CommandsManager;
import com.itmo.prog_lab5_8.commands.EchoCommand;
import com.itmo.prog_lab5_8.commands.ExitCommand;
import com.itmo.prog_lab5_8.commands.IncorrectCommandException;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String [] args) {
        CommandsManager commands = new CommandsManager();
        commands.addCommand(new ExitCommand());
        commands.addCommand(new EchoCommand());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                try {
                    commands.execute(reader.readLine());
                } catch (IncorrectCommandException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
