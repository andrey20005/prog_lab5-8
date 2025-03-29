package com.itmo.prog_lab5_8;

import com.itmo.prog_lab5_8.commands.CommandsManager;
import com.itmo.prog_lab5_8.commands.EchoCommand;
import com.itmo.prog_lab5_8.commands.ExitCommand;
import com.itmo.prog_lab5_8.commands.IncorrectCommandException;
import com.itmo.prog_lab5_8.utils.DragonsXmlConverter;
import com.itmo.prog_lab5_8.utils.TextStreamManager;
import com.itmo.prog_lab5_8.сollection.Dragons;
import com.itmo.prog_lab5_8.сollection.TestCollectionBuilder;

import javax.xml.bind.JAXBException;
import java.io.*;

public class Main {
    public static void main(String [] args) throws JAXBException {
        Dragons dragons = new TestCollectionBuilder().getCollection();

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
    }
}
