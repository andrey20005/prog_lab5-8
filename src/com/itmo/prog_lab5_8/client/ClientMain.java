package com.itmo.prog_lab5_8.client;


public class ClientMain {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 36666;
        Client client = new Client(host, port);
        while (true) {
            client.executeNextConsoleCommand();
        }
    }
}
