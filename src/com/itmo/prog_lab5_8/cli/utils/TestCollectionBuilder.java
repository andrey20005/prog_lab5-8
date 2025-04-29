package com.itmo.prog_lab5_8.cli.utils;

import com.itmo.prog_lab5_8.models.*;
import com.itmo.prog_lab5_8.models.Dragon;
import com.itmo.prog_lab5_8.сollection.Dragons;

import java.time.ZonedDateTime;

public class TestCollectionBuilder {
    public static Dragons getCollection() {
        Dragons dragons = new Dragons();

        Dragon dragon = new Dragon();
        dragon.setId(1L);
        dragon.setName("Smaug");
        dragon.setCoordinates(new Coordinates(68.648f, 684f));
        dragon.setCreationDate(ZonedDateTime.now());
        dragon.setAge(100);
        dragon.setDescription("A fearsome dragon");
        dragon.setWeight(500.0f);
        dragon.setCharacter(DragonCharacter.CUTTING);

//        Person killer = new Person();
//        killer.setName("Bard");
//        killer.setHeight(1.85);
//        killer.setEyeColor(Color.BLACK);
//        killer.setHairColor(Color.BROWN);
//        killer.setLocation(new Location());
//        killer.getLocation().setName("Lake-town");
//        killer.getLocation().setX(10);
//        killer.getLocation().setY(20);

        dragon.setKiller(
                new Person(
                        "Bard",
                        1.85,
                        Color.BLACK,
                        Color.BROWN,
                        new Location(10, 20, "Lake-town")
                )
        );
//        dragons.add(dragon);

        Dragon toothless = new Dragon();
        toothless.setId(2L);
        toothless.setName("Беззубик");
        toothless.setCoordinates(new Coordinates(45.7f, 88.2f));
        toothless.setCreationDate(ZonedDateTime.now());
        toothless.setAge(15);
        toothless.setDescription("Ночная фурия, очень дружелюбный");
        toothless.setWeight(350.0f);
        toothless.setCharacter(DragonCharacter.CHAOTIC);
//        dragons.add(toothless);

        return dragons;
    }
}
