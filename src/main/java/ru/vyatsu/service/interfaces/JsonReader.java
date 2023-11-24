package ru.vyatsu.service.interfaces;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import ru.vyatsu.service.interfaces.read.Reader;
import ru.vyatsu.service.structure.json.MangalibJson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.JsonParser;

public class JsonReader implements Reader<MangalibJson> {
    public MangalibJson read(String path) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            return ((new GsonBuilder().create()).fromJson(reader, MangalibJson.class)); // возвращаем ваш объект MangalibJson
        } catch (FileNotFoundException e) {
            throw new IOException("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("при чтении json файла", e);
        } catch (Exception e) {
            throw new IOException("непредвиденная", e);
        }
    }
}
