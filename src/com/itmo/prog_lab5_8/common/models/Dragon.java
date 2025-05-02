package com.itmo.prog_lab5_8.common.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dragon implements Comparable<Dragon> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private Float weight; //Значение поля должно быть больше 0, Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private Person killer; //Поле может быть null

    // нужно для jaxb
    public Dragon() {}

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
