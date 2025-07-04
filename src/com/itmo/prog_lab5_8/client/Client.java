package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.io.Console;
import com.itmo.prog_lab5_8.common.ClientRequester;
import java.io.IOException;

public class Client {
    private final ClientRequester requester;

    private final CommandsExecutor executor;

    public Client(String host, int port) {
        requester = new ClientRequester(host, port);
        executor = new CommandsExecutor(requester);
    }

    public void executeNextConsoleCommand() {
        Console console = new Console();
        try {
            executor.executeNextCommand(console);
        } catch (IllegalArgumentException e) {
            console.println(e.getMessage());
        } catch (IOException e) {
            console.println("с подключением к серверу произошли проблемы");
        } catch (ClassNotFoundException e) {
            console.println("сервер, почему-то отработал некорректно");
        }
    }
}
