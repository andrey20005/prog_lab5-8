package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.models.Dragon;

public interface Invoker {
    /**
     * @param dragon - id - is ignored
     */
    void add(Dragon dragon) throws IllegalArgumentException;
    void clear();
    void removeById(long id) throws IllegalArgumentException;
    String show();
    String info();
    boolean checkId(long id);
    void update(Dragon dragon);
    void save();
    default String echo(String text) {return text;}
}
