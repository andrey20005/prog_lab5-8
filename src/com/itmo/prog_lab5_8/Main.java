package com.itmo.prog_lab5_8;

import com.itmo.prog_lab5_8.commands.*;
import com.itmo.prog_lab5_8.models.Dragon;
import com.itmo.prog_lab5_8.utils.DragonsXmlConverter;
import com.itmo.prog_lab5_8.utils.io.Console;
import com.itmo.prog_lab5_8.utils.io.TextIO;
import com.itmo.prog_lab5_8.utils.io.TextStreamManager;
import com.itmo.prog_lab5_8.сollection.Dragons;
import com.itmo.prog_lab5_8.сollection.TestCollectionBuilder;

import javax.xml.bind.JAXBException;
import java.io.*;

public class Main {
    public static void main(String [] args) throws JAXBException, IOException {
        Dragons dragons = new TestCollectionBuilder().getCollection();
//        System.out.println(DragonsXmlConverter.toXml(DragonsXmlConverter.fromXML(DragonsXmlConverter.toXml(dragons))));

        CommandsManager commands = new CommandsManager();
        ExitCommand exitCommand = new ExitCommand();
        commands.addCommand(exitCommand);
        commands.addCommand(new EchoCommand());
        commands.addCommand(new HelpCommand(commands));
        commands.addCommand(new ShowCommand(dragons));

        TextIO console = new Console();
        while (exitCommand.running) {
            try {
                commands.execute(console.input("> "), console);
            } catch (IncorrectCommandException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
