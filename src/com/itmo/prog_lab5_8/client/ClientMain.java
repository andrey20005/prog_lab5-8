package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.construcrors.ConstructorsManager;
import com.itmo.prog_lab5_8.common.ClientRequester;

public class ClientMain {
    private static String host = "localhost";
    private static int port = 36666;
    public static void main(String[] args) {
        Client client = new Client(host, port);
        while (true) {
            client.executeNextConsoleCommand();
        }
    }
}
