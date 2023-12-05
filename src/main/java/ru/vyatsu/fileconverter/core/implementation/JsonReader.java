package ru.vyatsu.fileconverter.core.implementation;

import com.google.gson.GsonBuilder;
import ru.vyatsu.fileconverter.core.ApplicationException;
import ru.vyatsu.fileconverter.core.model.json.MangalibJson;
import ru.vyatsu.fileconverter.core.specification.Reader;

import java.io.*;

public class JsonReader implements Reader<MangalibJson> {

    public MangalibJson read(String path) throws ApplicationException {
        try (FileReader reader = new FileReader(path)) {
            return ((new GsonBuilder().create())
                    .fromJson(reader, MangalibJson.class));
        } catch (FileNotFoundException notFoundException) {
            throw new ApplicationException("Файл не найден: " + notFoundException.getMessage());
        } catch (IOException ioException) {
            throw new ApplicationException("Ошибка при чтении json файла", ioException);
        } catch (Exception exception) {
            throw new ApplicationException("Непредвиденная ошибка", exception);
        }
    }
}
