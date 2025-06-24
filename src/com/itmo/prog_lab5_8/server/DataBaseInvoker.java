package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Dragon;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DataBaseInvoker implements Invoker {
    private final DataBase db;

    public DataBaseInvoker(DataBase db) {
        this.db = db;
    }

    @Override
    public void registration(Account account) throws IncorrectRequestException {
        try {
            db.registration(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Dragon dragon, Account account) throws IncorrectRequestException {
        try {
            db.addDragon(dragon, account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка при работе с сервером");
        }
    }

    @Override
    public void clear(Account account) throws IncorrectRequestException {

    }

    @Override
    public void removeById(long id, Account account) throws IncorrectRequestException {

    }

    @Override
    public String show(Account account) throws IncorrectRequestException {
        try {
            return db.getDragons(account).toString();
//            return Arrays.toString(db.getDragons(account));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка при работе с сервера");
        }
    }

    @Override
    public String info(Account account) throws IncorrectRequestException {
        return "";
    }

    @Override
    public void update(Dragon dragon, Account account) throws IncorrectRequestException {

    }

    @Override
    public boolean checkAccount(Account account) throws IncorrectRequestException {
        try {
            return db.checkAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка работы сервера");
        }
    }
}
