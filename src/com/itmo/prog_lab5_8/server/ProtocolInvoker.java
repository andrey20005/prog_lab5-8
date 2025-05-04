package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class ProtocolInvoker implements Invoker {
    private final Invoker invoker;

    public ProtocolInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    private static void smartPrint(Object ... objects) {
        for (Object object : objects) {
            System.out.print(object.toString() + " ");
        }
        System.out.println();
    }

    @Override
    public void add(String name, float coordinateX, float coordinateY, int age, String description, float weight, DragonCharacter character) throws IllegalArgumentException {
        ProtocolInvoker.smartPrint(name, coordinateX, coordinateY, age, description, weight, character);
        invoker.add(name, coordinateX, coordinateY, age, description, weight, character);
    }

    @Override
    public void add(String name, float coordinateX, float coordinateY, int age, String description, float weight,
                    DragonCharacter character, String killerName, double killerHeight, Color killerEyeColor,
                    Color killerHeirColor, int killerLocationX, int killerLocationY,
                    String killerLocationName) throws IllegalArgumentException {
        ProtocolInvoker.smartPrint("add", name, coordinateX, coordinateY, age, description, weight, character,
                killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX, killerLocationY,
                killerLocationName);
        invoker.add(name, coordinateX, coordinateY, age, description, weight, character, killerName, killerHeight,
                killerEyeColor, killerHeirColor, killerLocationX, killerLocationY, killerLocationName);
    }

    @Override
    public void clear() {
        ProtocolInvoker.smartPrint("clear");
        invoker.clear();
    }

    @Override
    public void removeById(long id) throws IllegalArgumentException {
        ProtocolInvoker.smartPrint("remove_by_id", id);
        invoker.removeById(id);
    }

    @Override
    public String show() {
        ProtocolInvoker.smartPrint("show");
        return invoker.show();
    }

    @Override
    public boolean checkId(long id) {
        ProtocolInvoker.smartPrint("have_dragon", id);
        return invoker.checkId(id);
    }

    @Override
    public void update(long id, String name, float coordinateX, float coordinateY, int age, String description,
                       float weight, DragonCharacter character) throws IllegalArgumentException {
        ProtocolInvoker.smartPrint("update", id, name, coordinateX, coordinateY, age, description, weight, character);
        invoker.update(id, name, coordinateX, coordinateY, age, description, weight, character);
    }

    @Override
    public void update(long id, String name, float coordinateX, float coordinateY, int age, String description,
                       float weight, DragonCharacter character, String killerName, double killerHeight,
                       Color killerEyeColor, Color killerHeirColor, int killerLocationX, int killerLocationY,
                       String killerLocationName) throws IllegalArgumentException {
        ProtocolInvoker.smartPrint("update", id, name, coordinateX, coordinateY, age, description,
                weight, character, killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX,
                killerLocationY, killerLocationName);
        invoker.update(id, name, coordinateX, coordinateY, age, description,
                weight, character, killerName, killerHeight, killerEyeColor, killerHeirColor, killerLocationX,
                killerLocationY, killerLocationName);
    }

    @Override
    public void save() {
        ProtocolInvoker.smartPrint("save");
        invoker.save();
    }
}
