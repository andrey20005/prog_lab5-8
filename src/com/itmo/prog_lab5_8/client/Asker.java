package com.itmo.prog_lab5_8.client;

import com.itmo.prog_lab5_8.client.io.*;
import com.itmo.prog_lab5_8.common.models.Color;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asker {
    TextIO textIO;
    public Asker(TextIO textIO) {
        this.textIO = textIO;
    }

    private String getInput(String message, String prompt) {
        if (message.isEmpty()) return textIO.input(prompt);
        else return textIO.input(message + "\n" + prompt);
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
            throw new IllegalArgumentException("введи целое число");
        }
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("введи целое число");
        }
    }

    private static float parseFloat(String input) {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("введи число");
        }
    }

    private static <T extends Number> T isPositive(T numb) {
        if(numb.doubleValue() > 0) return numb;
        throw new IllegalArgumentException("число должно быть положительным");
    }

    private static String parseWord(String input) {
        input = input.trim();
        if(input.split(" ").length > 1) throw new IllegalArgumentException("нужно ввести одно слово");
        return input;
    }

    private static Color parseColor(String input) {
        input = input.toLowerCase();
        try {
            return Color.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("цвета " + input.trim() +
                    " найдено не было\nесть только: " +
                    Arrays.toString(Color.values()));
        }
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

    public long getPositiveLong(String prompt) {
        return ask("", prompt, ((Function<Long, Long>) Asker::isPositive).compose(Asker::parseLong));
    }

    public int getPositiveInt(String prompt) {
        return ask("", prompt, ((Function<Integer, Integer>) Asker::isPositive).compose(Asker::parseInt));
    }

    public float getPositiveFloat(String prompt) {
        return ask("", prompt, ((Function<Float, Float>) Asker::isPositive).compose(Asker::parseFloat));
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
}
