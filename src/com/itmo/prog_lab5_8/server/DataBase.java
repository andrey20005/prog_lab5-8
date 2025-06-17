package com.itmo.prog_lab5_8.server;


import java.sql.*;



public class DataBase {
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;

    // ssh -p 2222 -L 54327:pg:5432 helios
    public DataBase(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
        connection = DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(args[0], args[1], args[2]);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("\\d");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    private void checkConnection() throws SQLException {
        if (connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
    }


}
