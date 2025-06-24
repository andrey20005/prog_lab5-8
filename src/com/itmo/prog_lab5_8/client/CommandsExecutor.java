package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.construcrors.*;
import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.ClientRequester;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.commands.Command;

import java.io.IOException;
import java.util.Arrays;

public class CommandsExecutor {
    private final ClientRequester requester;
    private final Account account;
    private final LocalCommands localCommands;
    private final ConstructorsManager constructorsManager;

    public CommandsExecutor(ClientRequester requester, Account account) {
        this.requester = requester;
        this.account = account;
        constructorsManager = new ConstructorsManager(
                new AddConstructor(account),
                new UpdateConstructor(account),
                new ClearConstructor(account),
                new RemoveByIdConstructor(account),
                new ShowConstructor(account),
                new InfoConstructor(account),
                new EchoConstructor(),
                new RegistrationConstructor()
        );
        localCommands = new LocalCommands(this, constructorsManager, requester, account);
    }

    public void executeNextCommand(TextIO io) throws IncorrectRequestException, IOException, ClassNotFoundException {
        String[] input = io.input("> ").trim().split(" +");
        if (Arrays.equals(input, new String[]{""})) return;
        if (localCommands.containsCommand(input[0])) localCommands.execute(input, io);
        else {
            Command command = constructorsManager.getCommand(input, io);
            try {
                io.println(request(command));
            } catch (IOException e) {
                throw new IOException("проблема работы с сервером, проверьте подключение");
            }
        }
    }

    private String request(Command serverCommand) throws ClassNotFoundException, IOException {
        return requester.request(serverCommand).getResult();
    }
}
