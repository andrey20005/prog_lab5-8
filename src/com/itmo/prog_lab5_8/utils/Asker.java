package com.itmo.prog_lab5_8.utils;

import com.itmo.prog_lab5_8.io.Console;
import com.itmo.prog_lab5_8.io.TextIO;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asker {
    public static boolean yesNo(String prompt, TextIO io) throws IOException {
        String input = io.input(prompt);
        input = input.toLowerCase();
        do {
            if (input.contains("y") || input.contains("yes") || input.contains("д") || input.contains("да"))
                return true;
            else if (input.contains("n") || input.contains("no") || input.contains("н") || input.contains("нет"))
                return false;
        } while (io instanceof Console);
        throw new IOException("неверный ввод");
    }

    public static String ask(String prompt, TextIO io, Predicate<String> checker) throws IOException {
        String input = io.input(prompt);
        if (checker.test(input)) return input;
        else if (io instanceof Console) {
            do {
                input = io.input("неверный ввод\n" + prompt);
            } while (!checker.test(input));
            return input;
        } else throw new IOException("неверный ввод пользователя");
    }

    private static final Pattern word = Pattern.compile("(?U)^ *\\w+ *$");
    public static boolean isWord(String text) {
        Matcher matcher = word.matcher(text);
        return matcher.find();
    }

    public static Predicate<String> isConverted(Function<String, ?> converter) {
        return (String text) -> {
            try {
                converter.apply(text);
                return true;
            } catch (Exception e) {
                return false;
            }
        };
    }

    public static Predicate<String> mayNull(Predicate<String> checker) {
        return (String text) -> {
            return text.isEmpty() || checker.test(text);
        };
    }

    public static Predicate<String> notNull(Predicate<String> checker) {
        return (String text) -> {
            return !text.isEmpty() && checker.test(text);
        };
    }

    public static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNaturalNumber(String text) {
        try {
            return Integer.parseInt(text) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDecimal(String text) {
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveDecimal(String text) {
        try {
            return Float.parseFloat(text) >= 0.;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean anything(String text) {
        return true;
    }
}
