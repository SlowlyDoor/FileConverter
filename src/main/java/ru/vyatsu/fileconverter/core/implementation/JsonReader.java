package ru.vyatsu.fileconverter.core.implementation;

import com.google.gson.GsonBuilder;
import ru.vyatsu.fileconverter.core.model.json.MangalibJson;
import ru.vyatsu.fileconverter.core.specification.Reader;


import java.io.*;

public class JsonReader implements Reader<MangalibJson> {
    public MangalibJson read(String path) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            return ((new GsonBuilder().create())
                    .fromJson(reader, MangalibJson.class));
        } catch (FileNotFoundException e) {
            throw new IOException("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении json файла", e);
        } catch (Exception e) {
            throw new IOException("Непредвиденная ошибка", e);
        }
    }
}
