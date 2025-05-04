package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.ServerReceiver;
import com.itmo.prog_lab5_8.server.collection.Dragons;
import com.itmo.prog_lab5_8.server.collection.DragonsXmlConverter;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Main {
    private final static String path = ".testFiles/test.xml";
    private final static int port = 36666;
    public static void main(String[] args) {
        try {
            Dragons dragons;
            if (!new File(path).exists()) {
                System.out.println("будет создан новый файл");
                dragons = new Dragons(path);
            } else {
                dragons = DragonsXmlConverter.fromXMLFile(path);
            }
            ServerReceiver sr = new ServerReceiver(port);
            System.out.println("сервер поднят\n" + "порт: " + port);
            sr.run(new ProtocolInvoker(new ServerInvoker(dragons)));
        } catch (JAXBException|RuntimeException e) {
            System.out.println("не получилось открыть файл, скорее всего в нем допущена ошибка");
            throw new RuntimeException(e);
        }
    }
}
