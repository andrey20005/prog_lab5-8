package com.itmo.prog_lab5_8.cli.utils;

import com.itmo.prog_lab5_8.cli.io.Console;
import com.itmo.prog_lab5_8.cli.io.TextIO;

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

    private String ask(String message, String prompt, Predicate<String> checker) throws IllegalArgumentException {
        String text = prompt;
        if (message != null && message != "") text = message + "\n" + prompt;
        String input = textIO.input(text);
        if (checker.test(input)) return input;
        else throw new IllegalArgumentException();
    }

    private <T> T ask(String message, String prompt, Function<String, T> converter) throws IllegalArgumentException {
        String input = ask(message, prompt, (String t) -> true);
        try {
            return converter.apply(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private <T> T smartAsk(String message, String prompt, Function<String, T> converter) throws IllegalArgumentException {
        String input = ask(message, prompt, (String t) -> true);
        input = input.toLowerCase();
        try {
            return converter.apply(input);
        } catch (Exception e) {
            if (textIO instanceof Console) return smartAsk("неверный ввод: " + e.getMessage(), prompt, converter);
            throw new IllegalArgumentException("неверный ввод: " + e.getMessage());
        }
    }

    public <T> T ask(String prompt, Function<String, T> converter) throws IllegalArgumentException {
        return smartAsk("", prompt, converter);
    }

    private static final Pattern word = Pattern.compile("(?U)^ *(\\w+) *$");
    private String getWord(String message, String prompt) throws IllegalArgumentException {
        String text = ask(message, prompt, (String t) -> true);
        Matcher matcher = word.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            if (textIO instanceof Console) return getWord("нужно ввести слово", prompt);
            throw new IllegalArgumentException("нужно ввести слово");
        }
    }

    public String getWord(String prompt) throws IllegalArgumentException {return getWord("", prompt);}

    private int getInt(String message, String prompt) throws IllegalArgumentException {
        Function<String, Integer> f = Integer::parseInt;
        try {
            return ask(message, prompt, f);
        } catch (IllegalArgumentException e) {
            if (textIO instanceof Console) return getInt("нужно целое число", prompt);
            throw new IllegalArgumentException("нужно целое число");
        }
    }

    public int getInt(String prompt) throws IllegalArgumentException {return getInt("", prompt);}

    private int getPositiveInt(String message, String prompt) throws IllegalArgumentException {
        int res = getInt(message, prompt);
        if (res > 0) return res;
        else {
            if (textIO instanceof Console) return getPositiveInt("Число должно быть положительным", prompt);
            else throw new IllegalArgumentException("Число должно быть положительным");
        }
    }

    public int getPositiveInt(String prompt) throws IllegalArgumentException {return getPositiveInt("", prompt);}

    private float getFloat(String message, String prompt) throws IllegalArgumentException {
        Function<String, Float> f = Float::parseFloat;
        try {
            return ask(message, prompt, f);
        } catch (IllegalArgumentException e) {
            if (textIO instanceof Console) return getInt("нужно число", prompt);
            throw new IllegalArgumentException("нужно число");
        }
    }

    public float getFloat(String prompt) throws IllegalArgumentException {return getFloat("", prompt);}

    private float getPositiveFloat(String message, String prompt) throws IllegalArgumentException {
        float res = getFloat(message, prompt);
        if (res > 0) return res;
        else {
            if (textIO instanceof Console) return getPositiveFloat("число должно быть положительным", prompt);
            throw new IllegalArgumentException("число должно быть положительным");
        }
    }

    public float getPositiveFloat(String prompt) throws IllegalArgumentException {return getPositiveFloat("", prompt);}

    private long getLong(String message, String prompt) throws IllegalArgumentException {
        Function<String, Long> f = Long::getLong;
        try {
            return ask(message, prompt, f);
        } catch (IllegalArgumentException e) {
            if (textIO instanceof Console) return getInt("нужно целое число", prompt);
            throw new IllegalArgumentException("нужно целое число");
        }
    }

    public long getLong(String prompt) throws IllegalArgumentException {return getLong("", prompt);}

    private double getDouble(String message, String prompt) throws IllegalArgumentException {
        Function<String, Double> f = Double::parseDouble;
        try {
            return ask(message, prompt, f);
        } catch (IllegalArgumentException e) {
            if (textIO instanceof Console) return getInt("нужно число", prompt);
            throw new IllegalArgumentException("нужно число");
        }
    }

    public double getDouble(String prompt) throws IllegalArgumentException {return getDouble("", prompt);}

    private double getPositiveDouble(String message, String prompt) throws IllegalArgumentException {
        double res = getDouble(message, prompt);
        if (res > 0) return res;
        else {
            if (textIO instanceof Console) return getPositiveDouble("число должно быть положительным", prompt);
            throw new IllegalArgumentException("число должно быть положительным");
        }
    }

    public double getPositiveDouble(String prompt) throws IllegalArgumentException {return getPositiveDouble("", prompt);}


    private static String wordToBoolean(String word) {
        word = word.toLowerCase();
        if (Arrays.asList("yes", "y", "да", "д", "true", "t").contains(word)) return "true";
        else if (Arrays.asList("no", "n", "нет", "н", "false", "f").contains(word)) return "false";
        else throw new RuntimeException();
    }

    private boolean yesNo(String message, String prompt) throws IllegalArgumentException {
        try {
            String res = ask(message, prompt, Asker::wordToBoolean);
            return res == "true";
        } catch (Exception e) {
            if (textIO instanceof Console) return yesNo("нужно ввести да или нет", prompt);
            throw new IllegalArgumentException("нужно ввести да или нет");
        }
    }

    public boolean yesNo(String prompt) throws IllegalArgumentException {return yesNo("", prompt);}

    public String getString(String prompt) {
        try {
            return ask("", prompt, (String t) -> true);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ты что такое написал что я не понимаю тебя?");
        }
    }
}
