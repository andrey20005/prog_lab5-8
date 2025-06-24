package com.itmo.prog_lab5_8.server;


import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.models.*;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;


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

    public DataBase newConnection() throws SQLException {
        return new DataBase(url, user, password);
    }

    public void close() throws SQLException {
        connection.close();
    }

    private void checkConnection() throws SQLException {
        if (connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
    }

    public boolean checkAccount(Account account) throws SQLException, IncorrectRequestException {
        checkConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "select password_hash from prog_account where login = ?"
        )) {
            statement.setString(1, account.login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return account.getHash().equals(resultSet.getString(1));
            }
            else throw new IncorrectRequestException("такой логин не найден");
        }
    }

    public void registration(Account account) throws SQLException, IncorrectRequestException {
        checkConnection();
        try (
                PreparedStatement statement1 = connection.prepareStatement(
                        "select password_hash from prog_account where login = ?"
                );
                PreparedStatement statement2 = connection.prepareStatement(
                        "insert into prog_account values (?, ?);"
                )
                ) {
            statement1.setString(1, account.login);
            ResultSet resultSet = statement1.executeQuery();
            if (resultSet.next()) throw new IncorrectRequestException("такой логин уже используется");
            resultSet.close();
            statement2.setString(1, account.login);
            statement2.setString(2, account.getHash());
            statement2.executeUpdate();
        }
    }

    public void addDragon(Dragon dragon, Account account) throws SQLException, IncorrectRequestException {
        if (!checkAccount(account)) throw new IncorrectRequestException("неверный пароль");
        dragon.isCorrect();
        if (dragon.haveKiller()) {
            try (
                    PreparedStatement statement = connection.prepareStatement("insert into prog_dragon values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")
                    ) {
                statement.setString(1, account.login);
                statement.setString(2, dragon.getName());
                statement.setDouble(3, dragon.getCoordinates().getX());
                statement.setDouble(4, dragon.getCoordinates().getY());
                statement.setTimestamp(5, Timestamp.valueOf(dragon.getCreationDate().toLocalDateTime()));
                statement.setInt(6, dragon.getAge());
                statement.setString(7, dragon.getDescription());
                statement.setFloat(8, dragon.getWeight());
                statement.setString(9, dragon.getCharacter().toString());
                statement.setLong(10, addPerson(dragon.getKiller()));
                statement.executeUpdate();
            }
        } else {
            try (
                    PreparedStatement statement = connection.prepareStatement("insert into prog_dragon values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, null);")
            ) {
                statement.setString(1, account.login);
                statement.setString(2, dragon.getName());
                statement.setDouble(3, dragon.getCoordinates().getX());
                statement.setDouble(4, dragon.getCoordinates().getY());
                statement.setTimestamp(5, Timestamp.valueOf(dragon.getCreationDate().toLocalDateTime()));
                statement.setInt(6, dragon.getAge());
                statement.setString(7, dragon.getDescription());
                statement.setFloat(8, dragon.getWeight());
                statement.setString(9, dragon.getCharacter().toString());
                statement.executeUpdate();
            }
        }
    }

    protected long addLocation(Location location) throws SQLException {
        long new_id = 0;
        try (
                PreparedStatement statement = connection.prepareStatement("select id from prog_location order by id;")
                ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (new_id == resultSet.getLong("id")) {
                    new_id += 1;
                } else if (new_id < resultSet.getLong("id")) {
                    break;
                }
            }
        }
        try (
                PreparedStatement statement = connection.prepareStatement("insert into prog_location values (?, ?, ?, ?);")
                ) {
            statement.setLong(1, new_id);
            statement.setString(2, location.getName());
            statement.setInt(3, location.getX());
            statement.setInt(4, location.getY());
            statement.executeUpdate();
        }
        return new_id;
    }

    protected long addPerson(Person person) throws SQLException {
        long location_id = addLocation(person.getLocation());
        long new_id = 0;
        try (
                PreparedStatement statement = connection.prepareStatement("select id from prog_person order by id;")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (new_id == resultSet.getLong("id")) {
                    new_id += 1;
                } else if (new_id < resultSet.getLong("id")) {
                    break;
                }
            }
        }
        try (
                PreparedStatement statement = connection.prepareStatement("insert into prog_person values (?, ?, ?, ?, ?, ?);")
        ) {
            statement.setLong(1, new_id);
            statement.setString(2, person.getName());
            statement.setDouble(3, person.getHeight());
            statement.setString(4, person.getEyeColor().toString());
            statement.setString(5, person.getEyeColor().toString());
            statement.setInt(6, (int) location_id);
            statement.executeUpdate();
        }
        return new_id;
    }

    public Collection<Dragon> getDragons(Account account) throws IncorrectRequestException, SQLException {
        if (!checkAccount(account)) throw new IncorrectRequestException("неверный пароль");
        Collection<Dragon> dragons = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "select * from prog_dragon " +
                        "left join prog_person on killer = prog_person.id " +
                        "left join prog_location on location = prog_location.id " +
                        "where prog_dragon.account = ?;"
                )
                ) {
            statement.setString(1, account.login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("killer") == 0) {
                    dragons.add(new Dragon(
                            resultSet.getLong(1),
                            resultSet.getString(3),
                            resultSet.getFloat(4),
                            resultSet.getFloat(5),
                            resultSet.getTimestamp(6).toInstant().atZone(ZoneId.systemDefault()),
                            resultSet.getInt(7),
                            resultSet.getString(8),
                            resultSet.getFloat(9),
                            DragonCharacter.valueOf(resultSet.getString(10))
                    ));
                } else {
                    dragons.add(new Dragon(
                            resultSet.getLong(1),
                            resultSet.getString(3),
                            resultSet.getFloat(4),
                            resultSet.getFloat(5),
                            resultSet.getTimestamp(6).toInstant().atZone(ZoneId.systemDefault()),
                            resultSet.getInt(7),
                            resultSet.getString(8),
                            resultSet.getFloat(9),
                            DragonCharacter.valueOf(resultSet.getString(10)),
                            resultSet.getString(13),
                            resultSet.getDouble(14),
                            Color.valueOf(resultSet.getString(15)),
                            Color.valueOf(resultSet.getString(16)),
                            resultSet.getInt(20),
                            resultSet.getInt(21),
                            resultSet.getString(19)
                            ));
                }
            }
        }
        return dragons;
    }

    public void removeById(long id, Account account) throws SQLException, IncorrectRequestException {
        if (!checkId(id, account)) throw new IncorrectRequestException("нет дракона с таким id");
        try (
                PreparedStatement statement = connection.prepareStatement("delete from prog_dragon where id = ? and account = ?;")
                ) {
            statement.setLong(1, id);
            statement.setString(2, account.login);
            statement.executeUpdate();
        }
    }

    public boolean checkId(long id, Account account) throws IncorrectRequestException, SQLException {
        if (!checkAccount(account)) throw new IncorrectRequestException("неверный пароль");
        try (
                PreparedStatement statement = connection.prepareStatement("select * from prog_dragon where id = ? and account = ?;")
                ) {
            statement.setLong(1, id);
            statement.setString(2, account.login);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public void clear(Account account) throws SQLException, IncorrectRequestException {
        if (!checkAccount(account)) throw new IncorrectRequestException("неверный пароль");
        try (
                PreparedStatement statement = connection.prepareStatement("delete from prog_dragon where account = ?")
                ) {
            statement.setString(1, account.login);
            statement.executeUpdate();
        }
    }

    public void update(Dragon dragon, Account account) throws SQLException, IncorrectRequestException {
        dragon.isCorrect();
        if (!checkId(dragon.getId(), account)) throw new IncorrectRequestException("нет дракона с таким id");
        if (dragon.haveKiller()) {
            try (
                    PreparedStatement statement = connection.prepareStatement(
                            "update prog_dragon " +
                                    "set name = ?, coordinates_x = ?, coordinates_y = ?, creation_date = ?, age = ?, " +
                                    "description = ?, weight = ?, character = ?, killer = ? " +
                                    "where id = ?"
                    )
            ) {
                statement.setString(1, dragon.getName());
                statement.setFloat(2, dragon.getCoordinates().getX());
                statement.setFloat(3, dragon.getCoordinates().getY());
                statement.setTimestamp(4, Timestamp.valueOf(dragon.getCreationDate().toLocalDateTime()));
                statement.setInt(5, dragon.getAge());
                statement.setString(6, dragon.getDescription());
                statement.setFloat(7, dragon.getWeight());
                statement.setString(8, dragon.getCharacter().toString());
                statement.setLong(9, addPerson(dragon.getKiller()));
                statement.setLong(10, dragon.getId());
                statement.executeUpdate();
            }
        } else {
            try (
                    PreparedStatement statement = connection.prepareStatement(
                        "update prog_dragon " +
                            "set name = ?, coordinates_x = ?, coordinates_y = ?, creation_date = ?, age = ?, " +
                            "description = ?, weight = ?, character = ?, killer = null " +
                            "where id = ?"
                    )
                    ) {
                statement.setString(1, dragon.getName());
                statement.setFloat(2, dragon.getCoordinates().getX());
                statement.setFloat(3, dragon.getCoordinates().getY());
                statement.setTimestamp(4, Timestamp.valueOf(dragon.getCreationDate().toLocalDateTime()));
                statement.setInt(5, dragon.getAge());
                statement.setString(6, dragon.getDescription());
                statement.setFloat(7, dragon.getWeight());
                statement.setString(8, dragon.getCharacter().toString());
                statement.setLong(9, dragon.getId());
                statement.executeUpdate();
            }
        }
    }
}
