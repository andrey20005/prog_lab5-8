package com.itmo.prog_lab5_8;

import com.itmo.prog_lab5_8.commands.CommandsManager;
import com.itmo.prog_lab5_8.commands.EchoCommand;
import com.itmo.prog_lab5_8.commands.ExitCommand;
import com.itmo.prog_lab5_8.commands.IncorrectCommandException;
import com.itmo.prog_lab5_8.utils.TextStreamManager;

import java.io.*;

public class Main {
    public static void main(String [] args) {
        CommandsManager commands = new CommandsManager();
        commands.addCommand(new ExitCommand());
        commands.addCommand(new EchoCommand());

        Reader reader = new InputStreamReader(System.in);
        PrintStream writer = System.out;
        try (reader; writer) {
            TextStreamManager console = new TextStreamManager(reader, writer);
            while (true) {
                try {
                    commands.execute(console.input("> "));
                } catch (IncorrectCommandException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            while (true) {
//                try {
//                    commands.execute(reader.readLine());
//                } catch (IncorrectCommandException exception) {
//                    System.out.println(exception.getMessage());
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
