package com.itmo.prog_lab5_8.utils.io;

import java.io.IOException;

public class TextIOManager implements TextIO {
    private final TextInput textInput;
    private final TextOutput textOutput;

    public TextIOManager(TextInput textInput, TextOutput textOutput) {
        this.textInput = textInput;
        this.textOutput = textOutput;
    }

    @Override
    public void println(String text) {
        textOutput.println(text);
    }

    @Override
    public boolean ready() throws IOException {
        return textInput.ready();
    }

    @Override
    public String input() throws IOException {
        return textInput.input();
    }

    @Override
    public String input(String prompt) throws IOException {
        return textInput.input(prompt);
    }
}
