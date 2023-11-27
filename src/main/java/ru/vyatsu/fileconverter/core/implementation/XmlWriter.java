package ru.vyatsu.fileconverter.core.implementation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.fileconverter.core.model.xml.Mangalib;
import ru.vyatsu.fileconverter.core.specification.Writer;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlWriter implements Writer<Mangalib> {

    public void write(Mangalib mangalib, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            new XmlMapper().writerWithDefaultPrettyPrinter()
                    .writeValue(new File(path), mangalib);
        } catch (IOException e) {
            throw new IOException("Ошибка при записи xml файла", e);
        } catch (Exception e) {
            throw new IOException("Непредвиденная ошибка", e);
        }
    }
}
