package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.io.Console;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.ClientRequester;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;

import java.io.IOException;

public class Client {
    public final ClientRequester requester;
    public final CommandsExecutor executor;
    public final Account account;
    private final int port;

    public Client(String host, int port) {
        this.port = port;
        account = new Account("not init", "1");
        requester = new ClientRequester(host, port);
        executor = new CommandsExecutor(requester, account);
    }

    public void executeNextConsoleCommand() {
        Console console = new Console();
        try {
            executor.executeNextCommand(console);
        } catch (IncorrectRequestException e) {
            console.println(e.getMessage());
        } catch (IOException e) {
            console.println("с подключением к серверу произошли проблемы. port: " + port);
        } catch (ClassNotFoundException e) {
            console.println("сервер, почему-то отработал некорректно");
        }
    }
}
