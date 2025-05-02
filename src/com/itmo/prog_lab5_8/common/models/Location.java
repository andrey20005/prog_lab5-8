package com.itmo.prog_lab5_8.common.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
    private int x;
    private Integer y = 0; //Поле не может быть null
    private String name = "not named"; //Строка не может быть пустой, Поле может быть null

    public Location() {}

    public Location(int x, int y, String name) {
        setX(x);
        setY(y);
        setName(name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
