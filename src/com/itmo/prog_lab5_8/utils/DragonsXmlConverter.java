package com.itmo.prog_lab5_8.utils;

import com.itmo.prog_lab5_8.сollection.Dragons;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class DragonsXmlConverter {
    public static String toXml(Dragons dragons) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Dragons.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(dragons, writer);

        return writer.toString();
    }
}
