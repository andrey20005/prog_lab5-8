package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.models.Dragon;

public interface Invoker {
    void registration(Account account) throws IncorrectRequestException;
    void add(Dragon dragon, Account account) throws IncorrectRequestException;
    void clear(Account account) throws IncorrectRequestException;
    void removeById(long id, Account account) throws IncorrectRequestException;
    String show(Account account) throws IncorrectRequestException;
    String info(Account account) throws IncorrectRequestException;
    void update(Dragon dragon, Account account) throws IncorrectRequestException;
    default String echo(String text) {return text;}
    boolean checkAccount(Account account) throws IncorrectRequestException;
}
