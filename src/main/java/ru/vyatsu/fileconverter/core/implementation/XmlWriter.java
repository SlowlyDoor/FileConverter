package ru.vyatsu.fileconverter.core.implementation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;
import ru.vyatsu.fileconverter.core.specification.Writer;

import java.io.FileWriter;
import java.io.IOException;

public class XmlWriter implements Writer<MangalibXml> {
    public void write(MangalibXml mangalib, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            new XmlMapper().writerWithDefaultPrettyPrinter()
                    .writeValue(writer, mangalib);
        } catch (IOException ioException) {
            throw new IOException("Ошибка при записи xml файла", ioException);
        } catch (Exception exception) {
            throw new IOException("Непредвиденная ошибка", exception);
        }
    }
}
