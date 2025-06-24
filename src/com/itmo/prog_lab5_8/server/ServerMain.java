package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.ServerReceiver;

import java.sql.SQLException;

public class ServerMain {
    /**
     * @param args:
     *            0 - port
     *            1 - url to db (jdbc:postgresql://localhost:54327/studs)
     *            2 - user
     *            3 - password
     */
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        String url = args[1];
        String user = args[2];
        String password = args[3];
        Invoker invoker;
        try {
            invoker = new DataBaseInvoker(new DataBase(url, user, password));
        } catch (SQLException e) {
            throw new RuntimeException("не получилось подключиться к бд", e);
        }
        ServerReceiver sr = new ServerReceiver(port);
        System.out.println("сервер поднят\n" + "порт: " + port);
        sr.run(invoker);
    }
}
