package com.itmo.prog_lab5_8.cli.collectors;

import com.itmo.prog_lab5_8.cli.io.TextInput;

public class FloatArgument extends ArgumentsCollector<Float>{
    public FloatArgument(String prompt, TextInput textInput) {
        super(prompt, textInput);
    }

    @Override
    protected Float parseArgument(String input) throws IllegalArgumentException {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("должно быть передано число");
        }
    }
}
