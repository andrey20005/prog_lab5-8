package com.itmo.prog_lab5_8.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    private Float x; //Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates() {}

    public Coordinates(Float x, Float y) {
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
