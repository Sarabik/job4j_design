package ru.job4j.serialization.xml.family;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class ToPOJO {
    public static void main(String[] args) throws JAXBException {
        Family family1 = new Family("Ivanovi",
                new String[]{"Ivan", "Olga", "Lisa"},
                new Address("Moscow", "Markova", 52),
                true);
        String xml = toXML(family1);
        System.out.println(xml);
        Family family2 = toFamily(xml);
        System.out.println(family1.equals(family2));
    }

    public static String toXML(Family family) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Family.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(family, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static Family toFamily(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Family.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Family family;
        try (StringReader reader = new StringReader(xml)) {
            family = (Family) unmarshaller.unmarshal(reader);
        }
        return family;
    }
}
