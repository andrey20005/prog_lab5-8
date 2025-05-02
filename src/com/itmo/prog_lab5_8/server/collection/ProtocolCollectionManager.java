package com.itmo.prog_lab5_8.server.collection;

import com.itmo.prog_lab5_8.common.CollectionManager;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class ProtocolCollectionManager implements CollectionManager {
    private final CollectionManager cm;

    public ProtocolCollectionManager(CollectionManager cm) {
        this.cm = cm;
    }

    private static void smartPrint(Object ... objects) {
        for (Object object : objects) {
            System.out.print(object.toString() + " ");
        }
        System.out.println();
    }

    @Override
    public void add(String name, float coordinateX, float coordinateY, int age, String description, float weight, DragonCharacter character) throws IllegalArgumentException {
        ProtocolCollectionManager.smartPrint(name, coordinateX, coordinateY, age, description, weight, character);
        cm.add(name, coordinateX, coordinateY, age, description, weight, character);
    }

    @Override
    public void add(String name, float coordinateX, float coordinateY, int age, String description, float weight,
                    DragonCharacter character, String killerName, double killerHeight, Color killerEyeColor,
                    Color killerHeirColor, int killerLocationX, int killerLocationY,
                    String killerLocationName) throws IllegalArgumentException {
        ProtocolCollectionManager.smartPrint("add", name, coordinateX, coordinateY, age, description, weight, character,
                killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX, killerLocationY,
                killerLocationName);
        cm.add(name, coordinateX, coordinateY, age, description, weight, character, killerName, killerHeight,
                killerEyeColor, killerHeirColor, killerLocationX, killerLocationY, killerLocationName);
    }

    @Override
    public void clear() {
        ProtocolCollectionManager.smartPrint("clear");
        cm.clear();
    }

    @Override
    public void removeById(long id) throws IllegalArgumentException {
        ProtocolCollectionManager.smartPrint("remove_by_id", id);
        cm.removeById(id);
    }

    @Override
    public String show() {
        ProtocolCollectionManager.smartPrint("show");
        return cm.show();
    }

    @Override
    public boolean haveDragon(long id) {
        ProtocolCollectionManager.smartPrint("have_dragon", id);
        return cm.haveDragon(id);
    }

    @Override
    public void update(long id, String name, float coordinateX, float coordinateY, int age, String description,
                       float weight, DragonCharacter character) throws IllegalArgumentException {
        ProtocolCollectionManager.smartPrint("update", id, name, coordinateX, coordinateY, age, description, weight, character);
        cm.update(id, name, coordinateX, coordinateY, age, description, weight, character);
    }

    @Override
    public void update(long id, String name, float coordinateX, float coordinateY, int age, String description,
                       float weight, DragonCharacter character, String killerName, double killerHeight,
                       Color killerEyeColor, Color killerHeirColor, int killerLocationX, int killerLocationY,
                       String killerLocationName) throws IllegalArgumentException {
        ProtocolCollectionManager.smartPrint("update", id, name, coordinateX, coordinateY, age, description,
                weight, character, killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX,
                killerLocationY, killerLocationName);
        cm.update(id, name, coordinateX, coordinateY, age, description,
                weight, character, killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX,
                killerLocationY, killerLocationName);
    }

    @Override
    public void save() {
        ProtocolCollectionManager.smartPrint("save");
        cm.save();
    }
}
