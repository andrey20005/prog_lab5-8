package com.itmo.prog_lab5_8;

import com.itmo.prog_lab5_8.commands.*;
import com.itmo.prog_lab5_8.io.Console;
import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.utils.DragonsXmlConverter;
import com.itmo.prog_lab5_8.сollection.Dragons;
import com.itmo.prog_lab5_8.сollection.IncorrectInputException;

import javax.xml.bind.JAXBException;
import java.io.*;

public class Main {
    public static void main(String [] args) throws IOException {
        Dragons dragons = new Dragons();
        if (args.length != 1) {
            System.out.println("нужно ввести путь до файла");
            System.exit(0);
        } else if (!new File(args[0]).exists()) {
            System.out.println("будет создан новый файл");
        } else {
            try {
                dragons = DragonsXmlConverter.fromXMLFile(".testFiles/test.xml");
                System.out.println("Файл открыт");
            } catch (JAXBException e) {
                System.out.println("у данных в файле некорректный формат");
                System.exit(0);
            } catch (IncorrectInputException e) {
                System.out.println("данные в файле некорректны");
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
//        if (args.length == 1 && new File(args[0]).exists()) {
//            try {
//                dragons = DragonsXmlConverter.fromXMLFile(".testFiles/test.xml");
//                System.out.println("Файл открыт");
//            } catch (JAXBException e) {
//                System.out.println("у данных в файле некорректный формат");
//                System.exit(0);
//            } catch (IncorrectInputException e) {
//                System.out.println("данные в файле некорректны");
//                System.out.println(e.getMessage());
//                System.exit(0);
//            }
//        }
//        if (args.length == 1 && !new File(args[0]).exists()) {
//            System.out.println("будет создан новый файл");
//        }
//        if (args.length != 1) {
//            System.out.println("нужно ввести путь до файла");
//        }

        CommandsManager commands = new CommandsManager();
        ExitCommand exitCommand = new ExitCommand();
        commands.addCommand(exitCommand);
        commands.addCommand(new HelpCommand(commands));
        commands.addCommand(new ShowCommand(dragons));
        commands.addCommand(new InfoCommand(dragons));
        commands.addCommand(new AddCommand(dragons));
        commands.addCommand(new UpdateCommand(dragons));
        commands.addCommand(new RemoveByIdCommand(dragons));

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
