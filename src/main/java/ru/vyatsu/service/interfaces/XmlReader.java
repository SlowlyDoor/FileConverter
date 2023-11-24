package ru.vyatsu.service.interfaces;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.service.interfaces.read.Reader;
import ru.vyatsu.service.structure.xml.Mangalib;

import java.io.File;
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
            throw new IOException("при чтении xml файла", e);
        } catch (Exception e) {
            throw new IOException("непредвиденная ошибка", e);
        }
    }
}