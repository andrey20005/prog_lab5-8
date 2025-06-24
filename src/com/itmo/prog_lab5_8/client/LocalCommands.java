package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.construcrors.ConstructorsManager;
import com.itmo.prog_lab5_8.client.io.FileInput;
import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.client.io.TextIOManager;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.ClientRequester;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.commands.CheckAccount;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class LocalCommands {
    private final Map<String, LocalCommand> commands;
    private final CommandsExecutor executor;
    private final ConstructorsManager constructorsManager;
    private final ClientRequester requester;
    private final Account account;

    private record LocalCommand(String name, String help, BiConsumer<String[], TextIO> command) {}

    public LocalCommands(CommandsExecutor executor, ConstructorsManager constructorsManager, ClientRequester requester, Account account) {
        this.executor = executor;
        this.constructorsManager = constructorsManager;
        this.requester = requester;
        this.account = account;
        commands = new HashMap<>();
        commands.put(
                "help",
                new LocalCommand("help", "help: вывести справку по доступным командам", this::help)
        );
        commands.put(
                "exit",
                new LocalCommand("exit", "exit: завершить программу", this::exit)
        );
        commands.put(
                "execute_script",
                new LocalCommand(
                        "execute_script",
                        "execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте должны содержатся" +
                                "команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
                        this::executeScript
                )
        );
        commands.put(
                "login",
                new LocalCommand(
                        "login",
                        "login logint password: авторизоваться в системе",
                        this::login
                )
        );
    }

    public boolean containsCommand(String name) {return commands.containsKey(name);}

    public void execute(String[] input, TextIO io) {
        LocalCommand command = commands.get(input[0]);
        if (command != null) {
            command.command.accept(input, io);
        }
    }

    private void help(String[] input, TextIO io) {
        if (input.length > 1) throw new IllegalArgumentException("help не принимает аргументов");
        for (LocalCommand command : commands.values()) {
            io.println(command.help);
        }
        io.println(constructorsManager.getHelp());
    }

    private void exit(String[] input, TextIO io) {
        if (input.length > 1) throw new IllegalArgumentException("exit не принимает аргументов");
        System.exit(0);
    }

    private final Deque<String> paths = new ArrayDeque<>();
    private void executeScript(String[] input, TextIO io) {
        if (input.length != 2) throw new IllegalArgumentException("execute_script принимает один аргумент");
        if (paths.contains(input[1])) throw new IllegalArgumentException("скрипт вызывает сам себя");
        paths.push(input[1]);
        try {
            TextIO newIO = new TextIOManager(new FileInput(input[1]), io);
            while (newIO.ready()) {
                try {
                    executor.executeNextCommand(newIO);
                } catch (IncorrectRequestException e) {
                    io.println(e.getMessage());
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            io.println("файл с таким именем не найден: " + input[1]);
        }
        paths.pop();
    }

    private void login(String[] input, TextIO io) {
        if (input.length != 3) {
            io.println("команда принимает два аргумента: login password");
            return;
        }
        try {
            boolean isAccountCorrect = ((CheckAccount) requester.request(new CheckAccount(new Account(input[1], input[2])))).isAccountCorrect;
            if (isAccountCorrect) {
                account.login = input[1];
                account.password = input[2];
                io.println("авторизация прошла успешно");
            } else {
                io.println("авторизация не прошла успешно");
            }
        } catch (ClassNotFoundException e) {
            io.println("сервер отработал некорректно");
        } catch (IOException e) {
            io.println("ошибка при работе с сервером");
        }
    }
}
