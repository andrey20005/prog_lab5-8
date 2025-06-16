package com.itmo.prog_lab5_8.server.collection;

import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.Dragon;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;

public class Dragons {
    private ZonedDateTime creationTime;
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
        if (dragons.stream().allMatch(
                dragon -> dragons.stream().filter(dragon1 -> Objects.equals(dragon.getId(), dragon1.getId())).count() == 1
        )) {
            if (dragons.stream().allMatch(Dragon::isCorrect)){
                this.dragons = dragons;
            }
        } else throw new IllegalArgumentException("все id должны быть уникальны");
    }

    public int getLength() {
        return dragons.toArray().length;
    }

    public void add(Dragon dragon) throws IllegalArgumentException {
        dragon.isCorrect();
        dragon.setId(getUniqueID());
        dragons.add(dragon);
    }

    public void update(Dragon newDragon) {
        if (checkId(newDragon.getId())) {
            removeById(newDragon.getId());
            add(newDragon);
        } else throw new IllegalArgumentException("дракона с таким индексом не найдено");
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

    public boolean checkId(long id) {
        return dragons.stream().anyMatch(dragon -> dragon.getId() == id);
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
        if (!checkId(id)) throw new IllegalArgumentException("дракон с указанным id не найден");
        dragons.removeIf(dragon -> dragon.getId() == id);
    }

    public void clear() {
        dragons.clear();
    }

    public void addIfMin(Dragon newDragon) throws IllegalArgumentException {
        if (dragons.stream().allMatch(dragon -> newDragon.compareTo(dragon) < 0)) add(newDragon);
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

    public String show() {
        return this.toString();
    }
}
