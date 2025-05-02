package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public interface CollectionManager {
    void add(String name, float coordinateX, float coordinateY, int age, String description, float weight,
             DragonCharacter character) throws IllegalArgumentException;
    void add(String name, float coordinateX, float coordinateY, int age, String description, float weight,
             DragonCharacter character, String killerName, double killerHeight, Color killerEyeColor,
             Color killerHeirColor, int killerLocationX, int killerLocationY,
             String killerLocationName) throws IllegalArgumentException;
    void clear();
    void removeById(long id) throws IllegalArgumentException;
    String show();
    boolean haveDragon(long id);
    void update(long id, String name, float coordinateX, float coordinateY, int age, String description, float weight,
                DragonCharacter character) throws IllegalArgumentException;
    void update(long id, String name, float coordinateX, float coordinateY, int age, String description, float weight,
                DragonCharacter character, String killerName, double killerHeight, Color killerEyeColor,
                Color killerHeirColor, int killerLocationX, int killerLocationY,
                String killerLocationName) throws IllegalArgumentException;
    void save();
}
