package com.itmo.prog_lab5_8.сollection;

import com.itmo.prog_lab5_8.models.Coordinates;
import com.itmo.prog_lab5_8.models.Dragon;
import com.itmo.prog_lab5_8.models.DragonCharacter;

import java.time.ZonedDateTime;

public class TestCollectionBuilder implements CollectionBuilder{
    @Override
    public Dragons getCollection() {
        Dragons dragons = new Dragons();

        Dragon dragonA = new Dragon();
        dragonA.id = (long) 1;
        dragonA.name = "dragon_a";
        dragonA.coordinates = new Coordinates();
        dragonA.coordinates.x = (float) 1.;
        dragonA.coordinates.y = (float) 1.;
        dragonA.creationDate = ZonedDateTime.now();
        dragonA.age = 10;
        dragonA.description = "errger";
        dragonA.weight = (float) 500;
        dragonA.character = DragonCharacter.CHAOTIC;
        dragonA.killer = null;
        dragons.add(dragonA);

        Dragon dragonB = new Dragon();
        dragonB.id = (long) 2;
        dragonB.name = "dragon_b";
        dragonB.coordinates = new Coordinates();
        dragonB.coordinates.x = (float) 1.;
        dragonB.coordinates.y = (float) 10.;
        dragonB.creationDate = ZonedDateTime.now();
        dragonB.age = 100;
        dragonB.description = "errger";
        dragonB.weight = (float) 5000;
        dragonB.character = DragonCharacter.CUTTING;
        dragonB.killer = null;
        dragons.add(dragonB);

        Dragon dragonC = new Dragon();
        dragonC.id = (long) 1;
        dragonC.name = "dragon_c";
        dragonC.coordinates = new Coordinates();
        dragonC.coordinates.x = (float) 1.;
        dragonC.coordinates.y = (float) -200.;
        dragonC.creationDate = ZonedDateTime.now();
        dragonC.age = 105;
        dragonC.description = "errger";
        dragonC.weight = (float) 1000;
        dragonC.character = DragonCharacter.FICKLE;
        dragonC.killer = null;
        dragons.add(dragonC);

        return dragons;
    }
}
