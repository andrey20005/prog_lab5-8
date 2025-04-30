package com.itmo.prog_lab5_8.cli.commands;

import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.cli.utils.ScriptExecutor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteScriptCommand implements Command {
    private final ScriptExecutor executor;

    public ExecuteScriptCommand(ScriptExecutor scriptExecutor) {
        this.executor = scriptExecutor;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    private final Pattern commandPattern = Pattern.compile("(?U)^(\\w+) +(\\S+) *$");
    @Override
    public void execute(String command, TextIO textIO) {
        Matcher commandMatcher = commandPattern.matcher(command);
        if(!commandMatcher.find()) throw new IllegalArgumentException("команда должна содержать только путь до файла");
        executor.execute(commandMatcher.group(2), textIO);
    }
}
