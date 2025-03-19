package com.itmo.prog_lab5_8.models;

import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

public class Dragon implements Comparable<Dragon> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private Float weight; //Значение поля должно быть больше 0, Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private Person killer; //Поле может быть null

    @Override
    public int compareTo(@NotNull Dragon o) {
        return Float.compare(weight, o.weight);
    }
}
