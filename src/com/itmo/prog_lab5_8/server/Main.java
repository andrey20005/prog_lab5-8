package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.CollectionManager;
import com.itmo.prog_lab5_8.common.Invokers;
import com.itmo.prog_lab5_8.common.ServerReceiver;
import com.itmo.prog_lab5_8.server.collection.Dragons;
import com.itmo.prog_lab5_8.server.collection.DragonsXmlConverter;
import com.itmo.prog_lab5_8.server.collection.ProtocolCollectionManager;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Main {
    private final static String path = ".testFiles/test.xml";
    private final static int port = 36666;
    public static void main(String[] args) {
        try {
            CollectionManager cm;
            if (!new File(path).exists()) {
                System.out.println("будет создан новый файл");
                cm = new Dragons(path);
            } else {
                cm = DragonsXmlConverter.fromXMLFile(path);
            }
            cm = new ProtocolCollectionManager(cm);
            ServerReceiver sr = new ServerReceiver(port);
            System.out.println("сервер поднят\n" + "порт: " + port);
            sr.run(new Invokers(cm));
        } catch (JAXBException|RuntimeException e) {
            System.out.println("не получилось открыть файл, скорее всего в нем допущена ошибка");
            throw new RuntimeException(e);
        }
    }
}
