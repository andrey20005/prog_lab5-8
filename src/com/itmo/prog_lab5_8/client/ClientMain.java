package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.common.ClientRequester;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.commands.Show;

public class ClientMain {
    private static String host = "localhost";
    private static int port = 36666;
    public static void main(String[] args) {
        Command sh = new Show();
        Command res = new ClientRequester(host, port).request(sh);
        System.out.println(res.getResult());
    }
}
