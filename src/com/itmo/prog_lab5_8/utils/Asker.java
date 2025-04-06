package com.itmo.prog_lab5_8.utils;

import com.itmo.prog_lab5_8.io.Console;
import com.itmo.prog_lab5_8.io.TextIO;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asker {
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

    private static final Pattern word = Pattern.compile("^ *\\w+ *$");
    public static boolean isWord(String text) {
        Matcher matcher = word.matcher(text);
        return matcher.find();
    }

    public static boolean isConverted(String text, Function<String, ?> converter) {
        try {
            converter.apply(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNumber(String text) {
        try {
            Long.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String text) {
        try {
            Double.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
