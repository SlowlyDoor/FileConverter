package ru.vyatsu.service.interfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.vyatsu.service.interfaces.write.Writer;
import ru.vyatsu.service.structure.json.MangalibJson;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter implements Writer<MangalibJson> {
    public void write(MangalibJson mangalib, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(mangalib, writer);
        } catch (IOException e) {
            throw new IOException("при записи json файла", e);
        } catch (Exception e) {
            throw new IOException("непредвиденная", e);
        }
    }
}
