package com.itmo.prog_lab5_8.client.construcrors;

import com.itmo.prog_lab5_8.client.Asker;
import com.itmo.prog_lab5_8.client.io.TextIO;
import com.itmo.prog_lab5_8.common.commands.Add;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

public class AddConstructor extends AbstractCommandConstructor {
    public AddConstructor() {
        super("add", "add {element}: добавить новый элемент в коллекцию");
    }

    @Override
    public Command getCommand(String[] input, TextIO io) {
        if(input.length > 1) throw new IllegalArgumentException("присутствуют лишние аргументы");
        Asker asker = new Asker(io);
        String name = asker.getWord("имя дракона: ");
        float coordinateX = asker.getFloat("координата x: ");
        float coordinateY = asker.getFloat("координата y: ");
        int age = asker.getPositiveInt("возраст дракона: ");
        String description = asker.getText("описание дракона: ");
        float weight = asker.getPositiveFloat("вес дракона: ");
        DragonCharacter character = asker.getDragonCharacter("характер дракона: ");
        boolean haveKiller = asker.getYesNo("есть ли убийца[yes|no]: ");
        if(!haveKiller) return new Add(name, coordinateX, coordinateY, age, description, weight, character);
        else {
            return new Add(name, coordinateX, coordinateY, age, description, weight, character,
                    asker.getWord("имя убийцы: "),
                    asker.getPositiveDouble("рост убийцы: "),
                    asker.getColor("цвет глаз: "),
                    asker.getColor("цвет волос: "),
                    asker.getInt("координата Х убийцы: "),
                    asker.getInt("координата У убийцы: "),
                    asker.getWord("местоположение убийцы(название): ")
            );
        }
    }
}
