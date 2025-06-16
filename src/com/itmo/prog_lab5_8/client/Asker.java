package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.io.*;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.Dragon;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.function.Function;

public class Asker {
    TextIO textIO;
    public Asker(TextIO textIO) {
        this.textIO = textIO;
    }

    private String getInput(String message, String prompt) {
        if (message.isEmpty()) return textIO.input(prompt);
        else return textIO.input(message + "\n" + prompt);
    }

    public Dragon getDragon() {
        String name = getWord("имя дракона: ");
        float coordinateX = getFloat("координата x: ");
        float coordinateY = getFloat("координата y: ");
        int age = getPositiveInt("возраст дракона: ");
        String description = getText("описание дракона: ");
        float weight = getPositiveFloat("вес дракона: ");
        DragonCharacter character = getDragonCharacter("характер дракона: ");
        boolean haveKiller = getYesNo("есть ли убийца[yes|no]: ");
        if(!haveKiller) return new Dragon(-1L, name, coordinateX, coordinateY, ZonedDateTime.now(), age, description, weight, character);
        else return new Dragon(
                -1L, name, coordinateX, coordinateY, ZonedDateTime.now(), age, description, weight, character,
                getWord("имя убийцы: "),
                getPositiveDouble("рост убийцы: "),
                getColor("цвет глаз: "),
                getColor("цвет волос: "),
                getInt("координата Х убийцы: "),
                getInt("координата У убийцы: "),
                getWord("местоположение убийцы(название): ")
        );
    }

    public <T> T ask(String message, String prompt, Function<String, T> converter) {
        String input = getInput(message, prompt);
        try {
            return converter.apply(input);
        } catch (Exception e) {
            if (textIO instanceof Console) return ask(e.getMessage(), prompt, converter);
            throw e;
        }
    }

    private static long parseLong(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("введите целое число");
        }
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("введите целое число");
        }
    }

    private static float parseFloat(String input) {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("введите число");
        }
    }

    private static double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("введите число");
        }
    }

    private static <T extends Number> T isPositive(T numb) {
        if(numb.doubleValue() > 0) return numb;
        throw new IllegalArgumentException("число должно быть положительным");
    }

    private static String parseWord(String input) {
        if(input.trim().isEmpty()) throw new IllegalArgumentException("нельзя оставлять поле пустым");
        input = input.trim();
        if(input.split(" +").length > 1) throw new IllegalArgumentException("нужно ввести одно слово");
        return input;
    }

    private static Color parseColor(String input) {
        input = parseWord(input).toUpperCase();
        try {
            return Color.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("цвета " + input.trim() +
                    " найдено не было\nесть только: " +
                    Arrays.toString(Color.values()));
        }
    }

    private static DragonCharacter parseDragonCharacter(String input) {
        input = parseWord(input).toUpperCase();
        try {
            return DragonCharacter.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("характер " + input.trim() +
                    " найден не был\nесть только: " +
                    Arrays.toString(DragonCharacter.values()));
        }
    }

    private static boolean parseYesNo(String input) {
        if(input.trim().isEmpty()) throw new IllegalArgumentException("нельзя оставлять поле пустым");
        input = input.toLowerCase().trim();
        if(Arrays.asList("yes", "y", "да", "д", "true", "t").contains(input)) return true;
        if(Arrays.asList("no", "n", "но", "н", "false", "f").contains(input)) return false;
        throw new IllegalArgumentException("не получилось понять ответ[да|нет]");
    }

    public long getLong(String prompt) {
        return ask("", prompt, Asker::parseLong);
    }

    public int getInt(String prompt) {
        return ask("", prompt, Asker::parseInt);
    }

    public float getFloat(String prompt) {
        return ask("", prompt, Asker::parseFloat);
    }

    public double getDouble(String prompt) {
        return ask("", prompt, Asker::parseDouble);
    }

    public long getPositiveLong(String prompt) {
        return ask("", prompt, ((Function<Long, Long>) Asker::isPositive).compose(Asker::parseLong));
    }

    public int getPositiveInt(String prompt) {
        return ask("", prompt, ((Function<Integer, Integer>) Asker::isPositive).compose(Asker::parseInt));
    }

    public float getPositiveFloat(String prompt) {
        return ask("", prompt, ((Function<Float, Float>) Asker::isPositive).compose(Asker::parseFloat));
    }

    public double getPositiveDouble(String prompt) {
        return ask("", prompt, ((Function<Double, Double>) Asker::isPositive).compose(Asker::parseDouble));
    }

    public String getWord(String prompt) {
        return ask("", prompt, Asker::parseWord);
    }

    public String getText(String prompt) {
        return ask("", prompt, (text) -> text);
    }

    public Color getColor(String prompt) {
        return ask(Arrays.toString(Color.values()), prompt, Asker::parseColor);
    }

    public DragonCharacter getDragonCharacter(String prompt) {
        return ask(Arrays.toString(DragonCharacter.values()), prompt, Asker::parseDragonCharacter);
    }

    public boolean getYesNo(String prompt) {
        return ask("", prompt, Asker::parseYesNo);
    }
}
