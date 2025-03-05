package com.itmo.prog_lab5_8.сollection;

import com.itmo.prog_lab5_8.models.Dragon;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;

public class Dragons {
    private Collection<Dragon> dragons = new ArrayDeque<Dragon>();

    public Collection<Dragon> getDragons() {
        return dragons;
    }

    public int getLength() {
        return dragons.toArray().length;
    }

    public void add(Dragon newDragon) throws IncorrectObjectException {
        for (Dragon dragon : dragons) {
            if (dragon.id.equals(newDragon.id)) throw new IncorrectObjectException("Дракон с этим id уже существует");
        }
        dragons.add(newDragon);
    }

    public void addIfMin(Dragon newDragon) throws IncorrectObjectException {
        boolean flag = true;
        for (Dragon dragon : dragons) {
            if (newDragon.compareTo(dragon) < 0) flag = false;
        }
        if (flag) add(newDragon);
    }
}
