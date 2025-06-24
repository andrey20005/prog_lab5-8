package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.Account;
import com.itmo.prog_lab5_8.common.IncorrectRequestException;
import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Dragon;

import java.sql.SQLException;

public class DataBaseInvoker implements Invoker {
    private final DataBase db;

    public DataBaseInvoker(DataBase db) {
        this.db = db;
    }

    @Override
    public void registration(Account account) throws IncorrectRequestException {
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            localDB.registration(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void add(Dragon dragon, Account account) throws IncorrectRequestException {
        dragon.isCorrect();
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            localDB.addDragon(dragon, account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка при работе с сервером");
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void clear(Account account) throws IncorrectRequestException {
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            localDB.clear(account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка при работе с сервера");
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void removeById(long id, Account account) throws IncorrectRequestException {
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            localDB.removeById(id, account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка при работе с сервера");
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String show(Account account) throws IncorrectRequestException {
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return localDB.getDragons(account).toString();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка при работе с сервера");
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String info(Account account) throws IncorrectRequestException {
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return "Логин: " + account.login + "\nколичество драконов: " + localDB.getDragons(account).size();
        } catch (SQLException e) {
            throw new IncorrectRequestException("ошибка при работе с сервера");
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Dragon dragon, Account account) throws IncorrectRequestException {
        dragon.isCorrect();
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            localDB.update(dragon, account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка при работе с сервера");
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean checkAccount(Account account) throws IncorrectRequestException {
        DataBase localDB = null;
        try {
            localDB = db.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return localDB.checkAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IncorrectRequestException("ошибка работы сервера");
        } finally {
            try {
                localDB.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
