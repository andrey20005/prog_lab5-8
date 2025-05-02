package com.itmo.prog_lab5_8.server.collection;

import com.itmo.prog_lab5_8.common.CollectionManager;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.Dragon;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;
import com.itmo.prog_lab5_8.common.models.ZonedDateTimeAdapter;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;

public class Dragons implements CollectionManager {
    private ZonedDateTime creationTime = ZonedDateTime.now();
    private String path;
    private Collection<Dragon> dragons = new ArrayDeque<>();

    public Collection<Dragon> getDragons() {
        return dragons;
    }

    public Dragons(String path) {
        this.path = path;
        this.creationTime = ZonedDateTime.now();
    }

    public Dragons(String path, ZonedDateTime creationTime) {
        this.path = path;
        this.creationTime = creationTime;
    }

    public void setDragons(Collection<Dragon> dragons) {
        for (Dragon dragon : dragons) {
            if (Objects.equals(dragon.getId(), null)) throw new IllegalArgumentException("индекс дракона должен быть задан корректно");
            int counter = 0;
            for (Dragon dragon1 : dragons) if (Objects.equals(dragon.getId(), dragon1.getId())) counter++;
            if (counter >= 2) throw new IllegalArgumentException("Все индексы должны быть уникальными");
            if (Objects.equals(dragon.getName(), null) || Objects.equals(dragon.getName(), "")) throw new IllegalArgumentException("имя задано некорректно");
            if (Objects.equals(dragon.getCoordinates().getX(), null)) throw new IllegalArgumentException("координата X задана некорректно");
            if (Objects.equals(dragon.getCoordinates().getY(), null)) throw new IllegalArgumentException("координата Y задана некорректно");
            if (Objects.equals(dragon.getCreationDate(), null)) throw new IllegalArgumentException("время создания задано некорректно");
            if (dragon.getAge() <= 0) throw new IllegalArgumentException("возраст задан некорректно");
            if (Objects.equals(dragon.getWeight(), null) || dragon.getWeight() <= 0) throw new IllegalArgumentException("вес задан некорректно");
            if (Objects.equals(dragon.getCharacter(), null)) throw new IllegalArgumentException("характер задан некорректно");
            if (!Objects.equals(dragon.getKiller(), null)) {
                if (Objects.equals(dragon.getKiller().getName(), null)) throw new IllegalArgumentException("имя убийцы задано некорректно");
                if (dragon.getKiller().getHeight() <= 0) throw new IllegalArgumentException("рост убийцы задан некорректно");
                if (Objects.equals(dragon.getKiller().getEyeColor(), null)) throw new IllegalArgumentException("цвет глаз убийцы задано некорректно");
                if (Objects.equals(dragon.getKiller().getHairColor(), null)) throw new IllegalArgumentException("цвет волос убийцы задан некорректно");
                if (Objects.equals(dragon.getKiller().getLocation(), null)) throw new IllegalArgumentException("локация убийцы задана некорректно");
                if (Objects.equals(dragon.getKiller().getLocation().getY(), null)) throw new IllegalArgumentException("Y локации убийцы задано некорректно");
                if (Objects.equals(dragon.getKiller().getLocation().getName(), null)) throw new IllegalArgumentException("названия локации убийцы задано некорректно");
            }
        }
        this.dragons = dragons;
    }

    public int getLength() {
        return dragons.toArray().length;
    }

    public void add(Dragon dragon) throws IllegalArgumentException {
        if (Objects.equals(dragon.getName(), null)) throw new IllegalArgumentException("имя задано некорректно");
        if (Objects.equals(dragon.getCoordinates().getX(), null)) throw new IllegalArgumentException("координата X задана некорректно");
        if (Objects.equals(dragon.getCoordinates().getY(), null)) throw new IllegalArgumentException("координата Y задана некорректно");
        if (Objects.equals(dragon.getCreationDate(), null)) throw new IllegalArgumentException("время создания задано некорректно");
        if (dragon.getAge() <= 0) throw new IllegalArgumentException("возраст задан некорректно");
        if (Objects.equals(dragon.getWeight(), null) || dragon.getWeight() <= 0) throw new IllegalArgumentException("вес задан некорректно");
        if (Objects.equals(dragon.getCharacter(), null)) throw new IllegalArgumentException("характер задан некорректно");
        if (!Objects.equals(dragon.getKiller(), null)) {
            if (Objects.equals(dragon.getKiller().getName(), null)) throw new IllegalArgumentException("имя убийцы задано некорректно");
            if (dragon.getKiller().getHeight() <= 0) throw new IllegalArgumentException("рост убийцы задан некорректно");
            if (Objects.equals(dragon.getKiller().getEyeColor(), null)) throw new IllegalArgumentException("цвет глаз убийцы задано некорректно");
            if (Objects.equals(dragon.getKiller().getHairColor(), null)) throw new IllegalArgumentException("цвет волос убийцы задан некорректно");
            if (Objects.equals(dragon.getKiller().getLocation(), null)) throw new IllegalArgumentException("локация убийцы задана некорректно");
            if (Objects.equals(dragon.getKiller().getLocation().getY(), null)) throw new IllegalArgumentException("Y локации убийцы задано некорректно");
            if (Objects.equals(dragon.getKiller().getLocation().getName(), null)) throw new IllegalArgumentException("названия локации убийцы задано некорректно");
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
        if (Objects.equals(name, null) || name.isEmpty()) throw new IllegalArgumentException("нельзя передавать пустое имя");
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
        if (name.isEmpty()) throw new IllegalArgumentException("нельзя передавать пустое имя");
        if (Objects.equals(character, null)) throw new IllegalArgumentException("характер не может быть null");
        if (Objects.equals(killerName, null) || killerName.isEmpty()) throw new IllegalArgumentException("нельзя передавать пустое имя убийцы");
        if (Objects.equals(killerEyeColor, null)) throw new IllegalArgumentException("цвет глаз не может быть null");
        if (Objects.equals(killerHeirColor, null)) throw new IllegalArgumentException("цвет волос не может быть null");
        if (killerLocationName.isEmpty()) throw new IllegalArgumentException("нельзя передавать пустое имя локации");
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

    public boolean haveDragon(long id) {
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
        if (!haveDragon(id)) throw new IllegalArgumentException("дракон с указанным id не найден");
        dragons.removeIf(dragon -> dragon.getId() == id);
    }

    public void clear() {
        dragons.clear();
    }

    public void addIfMin(Dragon newDragon) throws IllegalArgumentException {
        boolean flag = true;
        for (Dragon dragon : dragons) {
            if (newDragon.compareTo(dragon) < 0) {
                flag = false;
                break;
            }
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

    public String getInfo() {
        return "путь до файла: " + getPath() + "\n" +
                "время создания: " + getCreationTime().toString() + "\n" +
                "количество элементов: " + getLength();
    }

    public void save() {
        DragonsXmlConverter.toXMLFile(this);
    }

    @Override
    public String toString() {
        return "Dragons{" + "\n" +
                "creationTime=" + creationTime + ",\n" +
                "path='" + path + '\'' + ",\n"  +
                "dragons=" + dragons + "\n"  +
                '}';
    }

    @Override
    public String show() {
        return this.toString();
    }
}
