package com.itmo.prog_lab5_8.commands;

import com.itmo.prog_lab5_8.io.FileInput;
import com.itmo.prog_lab5_8.io.TextIO;
import com.itmo.prog_lab5_8.io.TextIOManager;
import com.itmo.prog_lab5_8.io.TextInput;
import com.itmo.prog_lab5_8.utils.Asker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteScriptCommand implements Command {
    CommandsManager commandsManager;
    ExitCommand exitCommand;
    public ExecuteScriptCommand(CommandsManager commandsManager, ExitCommand exitCommand) {
        this.commandsManager = commandsManager;
        this.exitCommand = exitCommand;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public String getSyntax() {
        return "execute_script file_name";
    }

    private final Pattern commandPattern = Pattern.compile("(?U)^(\\w+) +(\\S+) *$");
    @Override
    public void execute(String command, TextIO textIO) {
        Matcher commandMatcher = commandPattern.matcher(command);
        if(!commandMatcher.find()) textIO.println("команда должна содержать только путь до файла");
        else {
            try {
                TextInput textInput = new FileInput(commandMatcher.group(2));
                TextIO newTextIO = new TextIOManager(textInput, textIO);
                while (exitCommand.running && newTextIO.ready()) {
                    commandsManager.execute(newTextIO.input(), newTextIO);
                }
                exitCommand.running = true;
            } catch (FileNotFoundException e) {
                textIO.println("путь до файла некорректен");
            } catch (IOException e) {
                textIO.println("файл закончился");
                exitCommand.running = true;
            }
        }
    }
}
