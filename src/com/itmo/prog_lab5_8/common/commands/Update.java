package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invokers;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class Update extends Command {
    private long id;
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

    public Update(long id, String name, float coordinateX, float coordinateY, int age, String description,
                  float weight, DragonCharacter character) {
        this.id = id;
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.character = character;
    }

    public Update(long id, String name, float coordinateX, float coordinateY, int age, String description,
               float weight, DragonCharacter character, String killerName, double killerHeight,
               Color killerEyeColor, Color killerHeirColor, int killerLocationX, int killerLocationY,
               String killerLocationName) {
        this.id = id;
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
    public void execute(Invokers invokers) {
        try {
            if (haveKiller) {
                invokers.cm.update(id, name, coordinateX, coordinateY, age, description, weight, character,
                        killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX, killerLocationY,
                        killerLocationName);
            } else {
                invokers.cm.update(id, name, coordinateX, coordinateY, age, description, weight, character);
            }
            result = "дракон был обновлен";
        } catch (RuntimeException e) {
            result = e.getMessage();
        }
    }
}
