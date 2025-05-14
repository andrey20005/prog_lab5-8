package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.commands.Command;

import java.io.*;
import java.net.Socket;

public class ClientRequester {
    private String host;
    private int port;

    public ClientRequester(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Command request(Command serverCommand) throws ClassNotFoundException, IOException {
        try(
                Socket socket = new Socket(host, port);
                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ) {
            oos.writeObject(serverCommand);
            return (Command) ois.readObject();
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
}
