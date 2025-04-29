package com.itmo.prog_lab5_8.cli.collectors;

import com.itmo.prog_lab5_8.cli.io.TextInput;

public class PositiveFloatArgument extends FloatArgument{
    public PositiveFloatArgument(String prompt, TextInput textInput) {
        super(prompt, textInput);
    }

    @Override
    protected Float parseArgument(String input) throws IllegalArgumentException {
        float ret = super.parseArgument(input);
        if (ret <= 0) throw new IllegalArgumentException("число должно быть положительным");
        return ret;
    }
}
