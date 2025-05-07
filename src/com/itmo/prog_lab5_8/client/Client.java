package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.construcrors.AddConstructor;
import com.itmo.prog_lab5_8.client.construcrors.ConstructorsManager;
import com.itmo.prog_lab5_8.client.io.Console;
import com.itmo.prog_lab5_8.common.ClientRequester;
import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.commands.Command;

public class Client {
    private final ClientRequester requester;
    private ConstructorsManager constructorsManager = new ConstructorsManager(
            new AddConstructor()
    );

    public Client(String host, int port) {
        requester = new ClientRequester(host, port);
    }

    public String request(Command serverCommand) {
        try {
            return requester.request(serverCommand).getResult();
        } catch (ClassNotFoundException e) {
            return "получен неправильный объект от сервера";
        }
    }

    public void executeNextConsoleCommand() {
        Console console = new Console();
        try {
            Command command = constructorsManager.getCommand(console.input("> ").trim().split(" +"), console);
            console.println(request(command));
        } catch (RuntimeException e) {
            console.println(e.getMessage());
        }
    }
}
