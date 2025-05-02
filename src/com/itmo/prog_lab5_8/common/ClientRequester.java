package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.commands.ServerCommand;

import java.io.*;
import java.net.Socket;

public class ClientRequester {
    private final String host;
    private final int port;

    public ClientRequester(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ServerCommand request(ServerCommand serverCommand) {
        try(
                Socket socket = new Socket(host, port);
                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ) {
            oos.writeObject(serverCommand);
            return (ServerCommand) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
