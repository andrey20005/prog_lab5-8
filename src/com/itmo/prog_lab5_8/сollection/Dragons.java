package com.itmo.prog_lab5_8.сollection;

import com.itmo.prog_lab5_8.models.Color;
import com.itmo.prog_lab5_8.models.Dragon;
import com.itmo.prog_lab5_8.models.DragonCharacter;

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
            if (Objects.equals(dragon.getId(), null)) throw new IncorrectInputException("индекс дракона должен быть задан корректно");
            int counter = 0;
            for (Dragon dragon1 : dragons) if (Objects.equals(dragon.getId(), dragon1.getId())) counter++;
            if (counter >= 2) throw new IncorrectInputException("Все индексы должны быть уникальными");
            if (Objects.equals(dragon.getName(), null) || dragon.getName() == "") throw new IncorrectInputException("имя задано некорректно");
            if (Objects.equals(dragon.getCoordinates().getX(), null)) throw new IncorrectInputException("координата X задана некорректно");
            if (Objects.equals(dragon.getCoordinates().getY(), null)) throw new IncorrectInputException("координата Y задана некорректно");
            if (Objects.equals(dragon.getCreationDate(), null)) throw new IncorrectInputException("время создания задано некорректно");
            if (dragon.getAge() <= 0) throw new IncorrectInputException("возраст задан некорректно");
            if (Objects.equals(dragon.getWeight(), null) || dragon.getWeight() <= 0) throw new IncorrectInputException("вес задан некорректно");
            if (Objects.equals(dragon.getCharacter(), null)) throw new IncorrectInputException("характер задан некорректно");
            if (!Objects.equals(dragon.getKiller(), null)) {
                if (Objects.equals(dragon.getKiller().getName(), null)) throw new IncorrectInputException("имя убийцы задано некорректно");
                if (dragon.getKiller().getHeight() <= 0) throw new IncorrectInputException("рост убийцы задан некорректно");
                if (Objects.equals(dragon.getKiller().getEyeColor(), null)) throw new IncorrectInputException("цвет глаз убийцы задано некорректно");
                if (Objects.equals(dragon.getKiller().getHairColor(), null)) throw new IncorrectInputException("цвет волос убийцы задан некорректно");
                if (Objects.equals(dragon.getKiller().getLocation(), null)) throw new IncorrectInputException("локация убийцы задана некорректно");
                if (Objects.equals(dragon.getKiller().getLocation().getY(), null)) throw new IncorrectInputException("Y локации убийцы задано некорректно");
                if (Objects.equals(dragon.getKiller().getLocation().getName(), null)) throw new IncorrectInputException("названия локации убийцы задано некорректно");
            }
        }
        this.dragons = dragons;
    }

    public int getLength() {
        return dragons.toArray().length;
    }

    public void add(Dragon dragon) throws IncorrectInputException {
        if (Objects.equals(dragon.getName(), null) || dragon.getName() == "") throw new IncorrectInputException("имя задано некорректно");
        if (Objects.equals(dragon.getCoordinates().getX(), null)) throw new IncorrectInputException("координата X задана некорректно");
        if (Objects.equals(dragon.getCoordinates().getY(), null)) throw new IncorrectInputException("координата Y задана некорректно");
        if (Objects.equals(dragon.getCreationDate(), null)) throw new IncorrectInputException("время создания задано некорректно");
        if (dragon.getAge() <= 0) throw new IncorrectInputException("возраст задан некорректно");
        if (Objects.equals(dragon.getWeight(), null) || dragon.getWeight() <= 0) throw new IncorrectInputException("вес задан некорректно");
        if (Objects.equals(dragon.getCharacter(), null)) throw new IncorrectInputException("характер задан некорректно");
        if (!Objects.equals(dragon.getKiller(), null)) {
            if (Objects.equals(dragon.getKiller().getName(), null)) throw new IncorrectInputException("имя убийцы задано некорректно");
            if (dragon.getKiller().getHeight() <= 0) throw new IncorrectInputException("рост убийцы задан некорректно");
            if (Objects.equals(dragon.getKiller().getEyeColor(), null)) throw new IncorrectInputException("цвет глаз убийцы задано некорректно");
            if (Objects.equals(dragon.getKiller().getHairColor(), null)) throw new IncorrectInputException("цвет волос убийцы задан некорректно");
            if (Objects.equals(dragon.getKiller().getLocation(), null)) throw new IncorrectInputException("локация убийцы задана некорректно");
            if (Objects.equals(dragon.getKiller().getLocation().getY(), null)) throw new IncorrectInputException("Y локации убийцы задано некорректно");
            if (Objects.equals(dragon.getKiller().getLocation().getName(), null)) throw new IncorrectInputException("названия локации убийцы задано некорректно");
        }
        Dragon newDragon = new Dragon();
        newDragon.setId(getUniqueID());
        newDragon.setName(dragon.getName());
        newDragon.setCoordinates(dragon.getCoordinates());
        newDragon.setCreationDate(dragon.getCreationDate());
        newDragon.setAge(dragon.getAge());
        newDragon.setDescription(dragon.getDescription());
        newDragon.setWeight(dragon.getWeight());
        newDragon.setCharacter(dragon.getCharacter());
        newDragon.setKiller(dragon.getKiller());
        dragons.add(newDragon);
    }

    public void add(String name,
                    float coordinateX,
                    float coordinateY,
                    int age,
                    String description,
                    float weight,
                    DragonCharacter character) {
        if (Objects.equals(name, null) || name == "") throw new IllegalArgumentException("нельзя передовать пустое имя");
        if (Objects.equals(character, null)) throw new IllegalArgumentException("характер не может быть null");
        Dragon dragon = new Dragon(
                getUniqueID(),
                name,
                coordinateX,
                coordinateY,
                ZonedDateTime.now(),
                age,
                description,
                weight,
                character
        );
        dragons.add(dragon);
    }

    public void add(String name,
                    float coordinateX,
                    float coordinateY,
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
        if (Objects.equals(name, null) || name == "") throw new IllegalArgumentException("нельзя передавать пустое имя");
        if (Objects.equals(character, null)) throw new IllegalArgumentException("характер не может быть null");
        if (Objects.equals(killerName, null) || killerName == "") throw new IllegalArgumentException("нельзя передавать пустое имя убийцы");
        if (Objects.equals(killerEyeColor, null)) throw new IllegalArgumentException("цвет глаз не может быть null");
        if (Objects.equals(killerHeirColor, null)) throw new IllegalArgumentException("цвет волос не может быть null");
        if (Objects.equals(killerLocationName, null) || killerLocationName == "") throw new IllegalArgumentException("нельзя передавать пустое имя локации");
        Dragon dragon = new Dragon(
                getUniqueID(),
                name,
                coordinateX,
                coordinateY,
                ZonedDateTime.now(),
                age,
                description,
                weight,
                character,
                killerName,
                killerHeight,
                killerEyeColor,
                killerHeirColor,
                killerLocationX,
                killerLocationY,
                killerLocationName
        );
        dragons.add(dragon);
    }

    private long getUniqueID() {
        long id = 0;
        boolean flag = true;
        while (flag) {
            id++;
            flag = false;
            for (Dragon dragon : dragons) {
                flag |= (dragon.getId().equals(id));
            }
        }
        return id;
    }

    public boolean hasDragonId(long id) {
        for (Dragon dragon : dragons) {
            if (dragon.getId() == id) return true;
        }
        return false;
    }

    public void update(long id,
                       String name,
                       float coordinateX,
                       float coordinateY,
                       int age,
                       String description,
                       float weight,
                       DragonCharacter character) {
        removeById(id);
        add(name, coordinateX, coordinateY, age, description, weight, character);
    }

    public void update(long id,
                       String name,
                       float coordinateX,
                       float coordinateY,
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
        removeById(id);
        add(name, coordinateX, coordinateY, age, description, weight, character,
                killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX, killerLocationY, killerLocationName);
    }

    public void removeById(long id) {
        if (!hasDragonId(id)) throw new IllegalArgumentException("дракон с указанным id не найден");
        dragons.removeIf(dragon -> dragon.getId() == id);
    }

    public void clear() {
        dragons.clear();
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
