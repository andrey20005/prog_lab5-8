package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.construcrors.*;
import com.itmo.prog_lab5_8.client.io.FileInput;
import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.client.io.TextIOManager;
import com.itmo.prog_lab5_8.common.ClientRequester;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Echo;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CommandsExecutor {
    private final ClientRequester requester;
    private final ConstructorsManager constructorsManager = new ConstructorsManager(
            new AddConstructor(),
            new UpdateConstructor(),
            new ClearConstructor(),
            new RemoveByIdConstructor(),
            new ShowConstructor()
    );

    public CommandsExecutor(ClientRequester requester) {
        this.requester = requester;
    }

    public void executeNextCommand(TextIO io) throws IllegalArgumentException, IOException, ClassNotFoundException {
        String[] input = io.input("> ").trim().split(" +");
        if (Arrays.equals(input, new String[]{""})) throw new IllegalArgumentException("");
        switch (input[0]) {
            case "exit" :
                System.exit(0);
                break;
            case "help" :
                System.out.println(constructorsManager.getDescription() +
                        "help: вывести справку по доступным командам\n" +
                        "exit: завершить программу\n" +
                        "execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся" +
                        " команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                        "connect [port] [host] - проверить подключение, поменять порт и хост"
                );
                break;
            case "execute_script" :
                try {
                    executeScript(input[1], io);
                } catch (IndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("нужно название файла");
                }
                break;
            case "connect" :
                try {
                    if (input.length == 2) requester.setPort(Integer.parseInt(input[1]));
                    else if (input.length == 3) {
                        requester.setHost(input[2]);
                        requester.setPort(Integer.parseInt(input[1]));
                    } else if (input.length > 3) {
                        throw new IllegalArgumentException("многовато аргументов");
                    }
                    io.println("host: " + requester.getHost() + "\nport: " + requester.getPort());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("порт должен быть числом");
                }
                io.println(request(new Echo("подключение успешно установленно")));
                break;
            default:
                Command command = constructorsManager.getCommand(input, io);
                try {
                    io.println(request(command));
                } catch (IOException e) {
                    throw new IOException("проблема работы с сервером, проверьте подключение");
                }
                break;
        }
    }

    private String request(Command serverCommand) throws ClassNotFoundException, IOException {
        return requester.request(serverCommand).getResult();
    }

    Deque<String> paths = new ArrayDeque<>();
    private void executeScript(String path, TextIO io) {
        if (paths.contains(path)) throw new IllegalArgumentException("скрипт вызывает сам себя");
        paths.push(path);
        try {
            TextIO newIO = new TextIOManager(new FileInput(path), io);
            while (newIO.ready()) {
                try {
                    executeNextCommand(newIO);
                } catch (IllegalArgumentException e) {
                    io.println(e.getMessage());
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            io.println("файл с таким именем не найден: " + path);
        }
        paths.pop();
    }
}
