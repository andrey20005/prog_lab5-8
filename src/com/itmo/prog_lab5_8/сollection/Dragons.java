package com.itmo.prog_lab5_8.сollection;

import com.itmo.prog_lab5_8.models.Dragon;

import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;

public class Dragons {
    private ZonedDateTime creationTime = ZonedDateTime.now();
    private String path;
    private Collection<Dragon> dragons = new ArrayDeque<>();

    public Collection<Dragon> getDragons() {
        return dragons;
    }

    public void setDragons(Collection<Dragon> dragons) throws IncorrectInputException {
        for (Dragon dragon : dragons) {
            int counter = 0;
            for (Dragon dragon1 : dragons) {
                if (Objects.equals(dragon.getId(), dragon1.getId())) counter++;
            }
            if (counter >= 2) throw new IncorrectInputException("Все индексы должны быть уникальными");
        }
        this.dragons = dragons;
    }

    public int getLength() {
        return dragons.toArray().length;
    }

    public void add(Dragon newDragon) throws IncorrectInputException {
        for (Dragon dragon : dragons) {
            if (dragon.getId().equals(newDragon.getId())) throw new IncorrectInputException("Дракон с этим id уже существует");
        }
        dragons.add(newDragon);
    }

    public void addIfMin(Dragon newDragon) throws IncorrectInputException {
        boolean flag = true;
        for (Dragon dragon : dragons) {
            if (newDragon.compareTo(dragon) < 0) flag = false;
        }
        if (flag) add(newDragon);
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
