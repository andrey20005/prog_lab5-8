package com.itmo.prog_lab5_8.common.models;

import com.itmo.prog_lab5_8.common.IncorrectRequestException;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Dragon implements Comparable<Dragon>, Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private Float weight; //Значение поля должно быть больше 0, Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private Person killer; //Поле может быть null

    public Dragon(Long id,
                  String name,
                  float coordinatesX,
                  float coordinatesY,
                  ZonedDateTime creationDate,
                  int age,
                  String description,
                  float weight,
                  DragonCharacter character) {
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(coordinatesX, coordinatesY);
        this.creationDate = creationDate;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.character = character;
    }

    public Dragon(Long id,
                  String name,
                  float coordinatesX,
                  float coordinatesY,
                  ZonedDateTime creationDate,
                  int age,
                  String description,
                  float weight,
                  DragonCharacter character,
                  String killerName,
                  double killerHeight,
                  Color killerEyeColor,
                  Color killerHeirColor,
                  int killerLocationX,
                  int killerLocationY,
                  String killerLocationName) {
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(coordinatesX, coordinatesY);
        this.creationDate = creationDate;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.character = character;
        this.killer = new Person(
                killerName,
                killerHeight,
                killerEyeColor,
                killerHeirColor,
                new Location(killerLocationX, killerLocationY, killerLocationName)
                );
    }

    public boolean haveKiller() {return killer != null;}

    public void isCorrect() throws IncorrectRequestException {
        if (Objects.equals(getName(), null)) throw new IncorrectRequestException("имя задано некорректно");
        if (Objects.equals(getCoordinates().getX(), null)) throw new IncorrectRequestException("координата X задана некорректно");
        if (Objects.equals(getCoordinates().getY(), null)) throw new IncorrectRequestException("координата Y задана некорректно");
        if (Objects.equals(getCreationDate(), null)) throw new IncorrectRequestException("время создания задано некорректно");
        if (getAge() <= 0) throw new IncorrectRequestException("возраст задан некорректно");
        if (Objects.equals(getWeight(), null) || getWeight() <= 0) throw new IncorrectRequestException("вес задан некорректно");
        if (Objects.equals(getCharacter(), null)) throw new IncorrectRequestException("характер задан некорректно");
        if (!Objects.equals(getKiller(), null)) {
            if (Objects.equals(getKiller().getName(), null)) throw new IncorrectRequestException("имя убийцы задано некорректно");
            if (getKiller().getHeight() <= 0) throw new IncorrectRequestException("рост убийцы задан некорректно");
            if (Objects.equals(getKiller().getEyeColor(), null)) throw new IncorrectRequestException("цвет глаз убийцы задано некорректно");
            if (Objects.equals(getKiller().getHairColor(), null)) throw new IncorrectRequestException("цвет волос убийцы задан некорректно");
            if (Objects.equals(getKiller().getLocation(), null)) throw new IncorrectRequestException("локация убийцы задана некорректно");
            if (Objects.equals(getKiller().getLocation().getY(), null)) throw new IncorrectRequestException("Y локации убийцы задано некорректно");
            if (Objects.equals(getKiller().getLocation().getName(), null)) throw new IncorrectRequestException("названия локации убийцы задано некорректно");
        }
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "\n\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tcoordinates=" + coordinates +
                ", \n\tcreationDate=" + creationDate +
                ", \n\tage=" + age +
                ", \n\tdescription='" + description + '\'' +
                ", \n\tweight=" + weight +
                ", \n\tcharacter=" + character +
                ", \n\tkiller=" + killer +
                "\n}";
    }

    @Override
    public int compareTo(Dragon o) {
        return Float.compare(weight, o.weight);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public Person getKiller() {
        return killer;
    }

    public void setKiller(Person killer) {
        this.killer = killer;
    }


}
