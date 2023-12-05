package ru.vyatsu.fileconverter.core.implementation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.fileconverter.core.ApplicationException;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;
import ru.vyatsu.fileconverter.core.specification.Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class XmlReader implements Reader<MangalibXml> {

    public MangalibXml read(String path) throws ApplicationException {
        try (FileReader reader = new FileReader(path)) {
            return (new XmlMapper()).readValue(reader, MangalibXml.class);
        } catch (FileNotFoundException notFoundException) {
            throw new ApplicationException("Файл не найден: " + notFoundException.getMessage());
        } catch (IOException ioException) {
            throw new ApplicationException("Ошибка при чтении xml файла", ioException);
        } catch (Exception exception) {
            throw new ApplicationException("Непредвиденная ошибка", exception);
        }
    }
}