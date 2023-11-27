package ru.vyatsu.fileconverter.core.implementation;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.fileconverter.core.model.xml.Mangalib;
import ru.vyatsu.fileconverter.core.specification.Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class XmlReader implements Reader<Mangalib> {

    public Mangalib read(String path) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            return (new XmlMapper()).readValue(reader, Mangalib.class);
        } catch (FileNotFoundException e) {
            throw new IOException("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении xml файла", e);
        } catch (Exception e) {
            throw new IOException("Непредвиденная ошибка", e);
        }
    }
}