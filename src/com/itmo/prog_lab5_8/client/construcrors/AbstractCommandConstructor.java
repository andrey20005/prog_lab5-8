package com.itmo.prog_lab5_8.client.construcrors;

public abstract class AbstractCommandConstructor implements CommandConstructor {
    private final String name;
    private final String description;

    public AbstractCommandConstructor(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
