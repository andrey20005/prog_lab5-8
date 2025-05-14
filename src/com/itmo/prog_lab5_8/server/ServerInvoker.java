package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Color;
import com.itmo.prog_lab5_8.common.models.Dragon;
import com.itmo.prog_lab5_8.common.models.DragonCharacter;
import com.itmo.prog_lab5_8.server.collection.Dragons;

public class ServerInvoker implements Invoker {
    private final Dragons dragons;

    public ServerInvoker(Dragons dragons) {
        this.dragons = dragons;
    }

    @Override
    public void add(Dragon dragon) {
        dragons.add(dragon);
    }

    @Override
    public void clear() {
        dragons.clear();
    }

    @Override
    public void removeById(long id) throws IllegalArgumentException {
        dragons.removeById(id);
    }

    @Override
    public String show() {
        return dragons.show();
    }

    @Override
    public boolean checkId(long id) {
        return dragons.checkId(id);
    }

    @Override
    public void update(Dragon dragon) {
        dragons.update(dragon);
    }

    @Override
    public void save() {
        dragons.save();
    }
}
