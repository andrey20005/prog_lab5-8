package com.itmo.prog_lab5_8.cli.utils;

import com.itmo.prog_lab5_8.cli.io.Console;
import com.itmo.prog_lab5_8.cli.io.TextIO;

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

    private String ask(String message, String prompt, Predicate<String> checker) throws IOException {
        String text = prompt;
        if (message != null && message != "") text = message + "\n" + prompt;
        String input = textIO.input(text);
        if (checker.test(input)) return input;
        else throw new IOException();
    }

    private <T> T ask(String message, String prompt, Function<String, T> converter) throws IOException {
        String input = ask(message, prompt, (String t) -> true);
        try {
            return converter.apply(input);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private <T> T smartAsk(String message, String prompt, Function<String, T> converter) throws IOException {
        String input = ask(message, prompt, (String t) -> true);
        try {
            return converter.apply(input);
        } catch (Exception e) {
            if (textIO instanceof Console) return smartAsk("неверный ввод", prompt, converter);
            throw new IOException(e);
        }
    }

    public <T> T ask(String prompt, Function<String, T> converter) throws IOException {
        return smartAsk("", prompt, converter);
    }

    private static final Pattern word = Pattern.compile("(?U)^ *(\\w+) *$");
    private String getWord(String message, String prompt) throws IOException {
        try {
            String text = ask(message, prompt, (String t) -> true);
            Matcher matcher = word.matcher(text);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                if (textIO instanceof Console) return getWord("нужно ввести слово", prompt);
                throw new IOException("нужно ввести слово");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWord(String prompt) throws IOException {return getWord("", prompt);}

    private int getInt(String message, String prompt) throws IOException {
        Function<String, Integer> f = Integer::parseInt;
        try {
            return ask(message, prompt, f);
        } catch (IOException e) {
            if (textIO instanceof Console) return getInt("нужно целое число", prompt);
            throw new IOException("нужно целое число");
        }
    }

    public int getInt(String prompt) throws IOException {return getInt("", prompt);}

    private int getPositiveInt(String message, String prompt) throws IOException {
        int res = getInt(message, prompt);
        if (res > 0) return res;
        else {
            if (textIO instanceof Console) return getPositiveInt("Число должно быть положительным", prompt);
            else throw new IOException("Число должно быть положительным");
        }
    }

    public int getPositiveInt(String prompt) throws IOException {return getPositiveInt("", prompt);}

    private float getFloat(String message, String prompt) throws IOException {
        Function<String, Float> f = Float::parseFloat;
        try {
            return ask(message, prompt, f);
        } catch (IOException e) {
            if (textIO instanceof Console) return getInt("нужно число", prompt);
            throw new IOException("нужно число");
        }
    }

    public float getFloat(String prompt) throws IOException {return getFloat("", prompt);}

    private float getPositiveFloat(String message, String prompt) throws IOException {
        float res = getFloat(message, prompt);
        if (res > 0) return res;
        else {
            if (textIO instanceof Console) return getPositiveFloat("число должно быть положительным", prompt);
            throw new IOException("число должно быть положительным");
        }
    }

    public float getPositiveFloat(String prompt) throws IOException {return getPositiveFloat("", prompt);}

    private long getLong(String message, String prompt) throws IOException {
        Function<String, Long> f = Long::getLong;
        try {
            return ask(message, prompt, f);
        } catch (IOException e) {
            if (textIO instanceof Console) return getInt("нужно целое число", prompt);
            throw new IOException("нужно целое число");
        }
    }

    public long getLong(String prompt) throws IOException {return getLong("", prompt);}

    private double getDouble(String message, String prompt) throws IOException {
        Function<String, Double> f = Double::parseDouble;
        try {
            return ask(message, prompt, f);
        } catch (IOException e) {
            if (textIO instanceof Console) return getInt("нужно число", prompt);
            throw new IOException("нужно число");
        }
    }

    public double getDouble(String prompt) throws IOException {return getDouble("", prompt);}

    private double getPositiveDouble(String message, String prompt) throws IOException {
        double res = getDouble(message, prompt);
        if (res > 0) return res;
        else {
            if (textIO instanceof Console) return getPositiveDouble("число должно быть положительным", prompt);
            throw new IOException("число должно быть положительным");
        }
    }

    public double getPositiveDouble(String prompt) throws IOException {return getPositiveDouble("", prompt);}


    private static String wordToBoolean(String word) {
        word = word.toLowerCase();
        if (Arrays.asList("yes", "y", "да", "д", "true", "t").contains(word)) return "true";
        else if (Arrays.asList("no", "n", "нет", "н", "false", "f").contains(word)) return "false";
        else throw new RuntimeException();
    }

    private boolean yesNo(String message, String prompt) throws IOException {
        try {
            String res = ask(message, prompt, Asker::wordToBoolean);
            return res == "true";
        } catch (Exception e) {
            if (textIO instanceof Console) return yesNo("нужно ввести да или нет", prompt);
            throw new IOException("нужно ввести да или нет");
        }
    }

    public boolean yesNo(String prompt) throws IOException {return yesNo("", prompt);}

    public String getString(String prompt) {
        try {
            return ask("", prompt, (String t) -> true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
