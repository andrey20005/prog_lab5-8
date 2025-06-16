package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.construcrors.*;
import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.ClientRequester;
import com.itmo.prog_lab5_8.common.commands.Command;

import java.io.IOException;
import java.util.Arrays;

public class CommandsExecutor {
    private final ClientRequester requester;
    private final ConstructorsManager constructorsManager = new ConstructorsManager(
            new AddConstructor(),
            new UpdateConstructor(),
            new ClearConstructor(),
            new RemoveByIdConstructor(),
            new ShowConstructor(),
            new InfoConstructor(),
            new EchoConstructor()
    );
    private final LocalCommands localCommands = new LocalCommands(this, constructorsManager);

    public CommandsExecutor(ClientRequester requester) {
        this.requester = requester;
    }

    public void executeNextCommand(TextIO io) throws IllegalArgumentException, IOException, ClassNotFoundException {
        String[] input = io.input("> ").trim().split(" +");
        if (Arrays.equals(input, new String[]{""})) throw new IllegalArgumentException("");
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
