package ru.vyatsu.service.converters;

import ru.vyatsu.service.structure.MangalibXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class DesirealizeXMLtoClass {
    public MangalibXML deserialize(String xmlFilePath) {
        try {
            // Создаем контекст JAXB
            JAXBContext context = JAXBContext.newInstance(MangalibXML.class);

            // Создаем десериализатор
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Десериализуем XML-файл в объект Mangalib
            return (MangalibXML) unmarshaller.unmarshal(new File(xmlFilePath));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
