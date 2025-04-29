package com.itmo.prog_lab5_8.cli.collectors;

import com.itmo.prog_lab5_8.cli.io.Console;
import com.itmo.prog_lab5_8.cli.io.TextInput;

import java.util.Objects;

public abstract class ArgumentsCollector<T> {
    private final String prompt;
    private final TextInput textInput;

    public ArgumentsCollector(String prompt, TextInput textInput) {
        this.prompt = prompt;
        this.textInput = textInput;
    }

    private final String getTextInput() {
        return textInput.input(prompt);
    }

    private final String getInput(String message) {
        if (Objects.equals(message, null)) return getTextInput();
        return textInput.input(message + "\n" + prompt);
    }

    protected abstract T parseArgument(String input) throws IllegalArgumentException;

    public final T getArgument() {
        return getArgument(null);
    }

    private final T getArgument(String message) {
        try {
            return parseArgument(getInput(message));
        } catch (IllegalArgumentException e) {
            if(textInput instanceof Console) return getArgument(e.getMessage());
            throw e;
        }
    }
}
