package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.commands.Command;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReceiver {
    private int port;
    protected ServerSocket serverSocket;
    public ServerReceiver(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(Invokers invokers) {
        while (true) {
            try(
                    Socket socket = serverSocket.accept();
                    OutputStream os = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    InputStream is = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                    ) {
                System.out.println("подключен клиент: " + socket.getInetAddress());
                Command command = (Command) ois.readObject();
                command.execute(invokers);
                invokers.cm.save();
                oos.writeObject(command);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("произошла ошибка при обработке запроса\nошибка: " + e.getMessage());
            }
        }
    }
}
