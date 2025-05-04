package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class Add extends AbstractStandardCommand {
    private String name;
    private float coordinateX;
    private float coordinateY;
    private int age;
    private String description;
    private float weight;
    private DragonCharacter character;
    private boolean haveKiller = false;
    private String killerName;
    private double killerHeight;
    private Color killerEyeColor;
    private Color killerHeirColor;
    private int killerLocationX;
    private int killerLocationY;
    private String killerLocationName;

    public Add(String name, float coordinateX, float coordinateY, int age, String description,
               float weight, DragonCharacter character) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.character = character;
    }

    public Add(String name, float coordinateX, float coordinateY, int age, String description,
               float weight, DragonCharacter character, String killerName, double killerHeight,
               Color killerEyeColor, Color killerHeirColor, int killerLocationX, int killerLocationY,
               String killerLocationName) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.character = character;
        this.killerName = killerName;
        this.killerHeight = killerHeight;
        this.killerEyeColor = killerEyeColor;
        this.killerHeirColor = killerHeirColor;
        this.killerLocationX = killerLocationX;
        this.killerLocationY = killerLocationY;
        this.killerLocationName = killerLocationName;
        haveKiller = true;
    }

    @Override
    public void execute(Invoker invoker) {
        try {
            if (haveKiller) invoker.add(name, coordinateX, coordinateY, age, description, weight, character,
                    killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX, killerLocationY,
                    killerLocationName);
            else invoker.add(name, coordinateX, coordinateY, age, description, weight, character);
            result = "в коллекцию успешно добавлен новый дракон";
        } catch (IllegalArgumentException e) {
            result = "переданные аргументы не корректны: \n" + e.getMessage();
        } catch (RuntimeException e) {
            result = "во время исполнения команды произошла ошибка:\n" + e.getMessage();
        }
    }
}
