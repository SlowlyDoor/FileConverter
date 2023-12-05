package ru.vyatsu.fileconverter.core.implementation;

import com.google.gson.GsonBuilder;
import ru.vyatsu.fileconverter.core.ApplicationException;
import ru.vyatsu.fileconverter.core.model.json.MangalibJson;
import ru.vyatsu.fileconverter.core.specification.Writer;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter implements Writer<MangalibJson> {

    public void write(MangalibJson mangalib, String path) throws ApplicationException {
        try (FileWriter writer = new FileWriter(path)) {
            new GsonBuilder().setPrettyPrinting()
                    .create()
                    .toJson(mangalib, writer);
        } catch (IOException ioException) {
            throw new ApplicationException("Ошибка при записи json файла", ioException);
        } catch (Exception exception) {
            throw new ApplicationException("Непредвиденная ошибка", exception);
        }
    }
}
