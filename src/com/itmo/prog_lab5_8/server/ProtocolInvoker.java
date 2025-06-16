package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.models.Dragon;

public class ProtocolInvoker implements Invoker {
    private final Invoker invoker;

    public ProtocolInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    private static void smartPrint(Object ... objects) {
        for (Object object : objects) {
            System.out.print(object.toString() + " ");
        }
        System.out.println();
    }

    @Override
    public void add(Dragon dragon) {
        System.out.println("add");
        ProtocolInvoker.smartPrint(dragon);
        invoker.add(dragon);
    }

    @Override
    public void clear() {
        ProtocolInvoker.smartPrint("clear");
        invoker.clear();
    }

    @Override
    public void removeById(long id) throws IllegalArgumentException {
        ProtocolInvoker.smartPrint("remove_by_id", id);
        invoker.removeById(id);
    }

    @Override
    public String show() {
        ProtocolInvoker.smartPrint("show");
        return invoker.show();
    }

    @Override
    public String info() {
        ProtocolInvoker.smartPrint("info");
        return invoker.info();
    }

    @Override
    public boolean checkId(long id) {
        ProtocolInvoker.smartPrint("have_dragon", id);
        return invoker.checkId(id);
    }

    @Override
    public void update(Dragon dragon) {
        System.out.println("add");
        ProtocolInvoker.smartPrint(dragon);
        invoker.update(dragon);
    }

    @Override
    public void save() {
        ProtocolInvoker.smartPrint("save");
        invoker.save();
    }
}
