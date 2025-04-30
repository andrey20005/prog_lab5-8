package com.itmo.prog_lab5_8.cli.utils;

import com.itmo.prog_lab5_8.cli.CommandsManager;
import com.itmo.prog_lab5_8.cli.io.FileInput;
import com.itmo.prog_lab5_8.cli.io.TextIO;
import com.itmo.prog_lab5_8.cli.io.TextIOManager;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;

public class ScriptExecutor {
    private final CommandsManager commandsManager;
    private final Deque<Path> stack = new ArrayDeque<>();

    public ScriptExecutor(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    public void execute(String path, TextIO textIO) {
        FileInput input;
        try {
            input = new FileInput(path);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("путь до файла не корректен");
        }
        Path newPath = Path.of(path);
        if (stack.contains(newPath)) throw new RuntimeException("файл вызывает сам себя");
        stack.push(newPath);
        try {
            TextIO newTextIO = new TextIOManager(input, textIO);
            while (commandsManager.isRunning() && newTextIO.ready()) {
                commandsManager.execute(newTextIO.input(), newTextIO);
            }
        } catch (RuntimeException e) {
            stack.pop();
            throw new RuntimeException("во время исполнения скрипта произошла ошибка" + "\n" +
                    input.getLastInput() + "\n" + e.getMessage());
        }
        commandsManager.setRunning(true);
        stack.pop();
    }
}
