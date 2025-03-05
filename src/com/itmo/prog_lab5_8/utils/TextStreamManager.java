package com.itmo.prog_lab5_8.utils;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class TextStreamManager {
    private Reader reader;
    private PrintStream writer;

    public TextStreamManager(@NotNull Reader reader, @NotNull PrintStream writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public TextStreamManager(@NotNull Reader reader) {
        this.reader = reader;
        this.writer = null;
    }

    public String input(String ps) throws IOException {
        if (writer != null) writer.print(ps);
        BufferedReader b = new BufferedReader(reader);
        return b.readLine();
    }
}
