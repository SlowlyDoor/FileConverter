package ru.vyatsu.service.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.service.interfaces.write.Writer;
import ru.vyatsu.service.structure.xml.Mangalib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlWriter implements Writer<Mangalib> {

    public void write(Mangalib mangalib, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            ObjectMapper xmlMapper = new XmlMapper();
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), mangalib);
        } catch (IOException e) {
            throw new IOException("при записи xml файла", e);
        } catch (Exception e) {
            throw new IOException("непредвиденная", e);
        }
    }
}
