package com.itmo.prog_lab5_8.models;

import java.time.ZonedDateTime;

public class Dragon {
    public Long id;
    public String name;
    public Coordinates coordinates;
    public ZonedDateTime creationDate;
    public int age;
    public String description;
    public float weight;
    public DragonCharacter character;
    public Person killer;
}
