package com.itmo.prog_lab5_8.utils;

import com.itmo.prog_lab5_8.models.DragonsWrapper;
import com.itmo.prog_lab5_8.сollection.Dragons;
import com.itmo.prog_lab5_8.сollection.IncorrectInputException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class DragonsXmlConverter {
    private static String toXML(DragonsWrapper dragons) {
        try {
            JAXBContext context = JAXBContext.newInstance(DragonsWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(dragons, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toXML(Dragons dragons) {
        DragonsWrapper dragonsWrapper = new DragonsWrapper();
        dragonsWrapper.setDragons(dragons.getDragons());
        dragonsWrapper.setCreationTime(dragons.getCreationTime());
        return DragonsXmlConverter.toXML(dragonsWrapper);
    }

    public static void toXMLFile(Dragons dragons) {
        try (Writer writer = new FileWriter(dragons.getPath())) {
            writer.write(toXML(dragons));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Dragons fromXMLFile(String path) throws JAXBException, IncorrectInputException {
        JAXBContext context = JAXBContext.newInstance(DragonsWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        DragonsWrapper dragonsWrapper = (DragonsWrapper) unmarshaller.unmarshal(new File(path));

        Dragons dragons = new Dragons();
        dragons.setPath(path);
        dragons.setCreationTime(dragonsWrapper.getCreationTime());
        dragons.setDragons(dragonsWrapper.getDragons());
        return dragons;
    }
}
