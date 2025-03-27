package com.itmo.prog_lab5_8.сollection;

import com.itmo.prog_lab5_8.models.Dragon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;

@XmlRootElement(name = "dragons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dragons {
    @XmlElement(name = "dragon")
    private Collection<Dragon> dragons = new ArrayDeque<Dragon>();

    public Collection<Dragon> getDragons() {
        return dragons;
    }

    public void setDragons(Collection<Dragon> dragons) {
        this.dragons = dragons;
    }

    public int getLength() {
        return dragons.toArray().length;
    }

    public void add(Dragon newDragon) throws IncorrectObjectException {
        for (Dragon dragon : dragons) {
            if (dragon.getId().equals(newDragon.getId())) throw new IncorrectObjectException("Дракон с этим id уже существует");
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
