package com.itmo.prog_lab5_8;

import com.itmo.prog_lab5_8.cli.CommandsManager;
import com.itmo.prog_lab5_8.cli.commands.*;
import com.itmo.prog_lab5_8.cli.io.Console;
import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.cli.utils.DragonsXmlConverter;
import com.itmo.prog_lab5_8.cli.utils.ScriptExecutor;
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
            dragons.setPath(args[0]);
        } else {
            try {
                dragons = DragonsXmlConverter.fromXMLFile(args[0]);
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

        CommandsManager commands = new CommandsManager();
        commands.addCommand(new ExitCommand(commands));
        commands.addCommand(new HelpCommand(commands));
        commands.addCommand(new ShowCommand(dragons));
        commands.addCommand(new InfoCommand(dragons));
        commands.addCommand(new AddCommand(dragons));
        commands.addCommand(new UpdateCommand(dragons));
        commands.addCommand(new RemoveByIdCommand(dragons));
        commands.addCommand(new ClearCommand(dragons));
        commands.addCommand(new SaveCommand(dragons));
        commands.addCommand(new EchoCommand());
        commands.addCommand(new ExecuteScriptCommand(new ScriptExecutor(commands)));

        TextIO console = new Console();
        while (commands.isRunning()) {
            try {
                commands.execute(console.input("> "), console);
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
