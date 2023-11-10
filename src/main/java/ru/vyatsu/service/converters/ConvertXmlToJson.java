package ru.vyatsu.service.converters;

import ru.vyatsu.service.structure.Mangalib;
import ru.vyatsu.service.structure.Manhwa;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConvertXmlToJson
{
    public void Converter(String xmlPath, String jsonPath)
    {
        Mangalib manhws = deserialize(xmlPath);
        WriteToJson(jsonPath, manhws);
    }
    private Mangalib deserialize(String xmlFilePath) {
        try {
            // Создаем контекст JAXB
            JAXBContext context = JAXBContext.newInstance(Mangalib.class);

            // Создаем десериализатор
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Десериализуем XML-файл в объект Mangalib
            return (Mangalib) unmarshaller.unmarshal(new File(xmlFilePath));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void WriteToJson(String jsonPath, Mangalib mangalib) {
        // Создаем словарь для хранения манг по авторам
        Map<String, JsonArrayBuilder> authorMap = new LinkedHashMap<>(); // Используем LinkedHashMap для сохранения порядка

        for (Manhwa manhwa : mangalib.getMangalib()) {
            // Создаем объекты для манги и ее переводчиков
            JsonObjectBuilder manhwaBuilder = Json.createObjectBuilder();
            manhwaBuilder.add("title", manhwa.getTitle());
            manhwaBuilder.add("publicationYear", manhwa.getPublicationYear());
            manhwaBuilder.add("chapters", manhwa.getChapters());

            JsonObjectBuilder translationTeamBuilder = Json.createObjectBuilder();
            translationTeamBuilder.add("publicationProjects", manhwa.getTranslationTeam().getPublicationProjects());
            translationTeamBuilder.add("adminName", manhwa.getTranslationTeam().getAdminName());
            translationTeamBuilder.add("name", manhwa.getTranslationTeam().getName());

            manhwaBuilder.add("translationTeam", translationTeamBuilder);

            // Получаем имя автора
            String authorName = manhwa.getAuthor();

            // Добавляем мангу в соответствующую группу в словаре
            authorMap.computeIfAbsent(authorName, k -> Json.createArrayBuilder())
                    .add(manhwaBuilder);
        }

        // Создаем объекты для авторов и манг по авторам
        JsonArrayBuilder authorsBuilder = Json.createArrayBuilder();

        for (Map.Entry<String, JsonArrayBuilder> entry : authorMap.entrySet()) {
            JsonObjectBuilder authorBuilder = Json.createObjectBuilder();
            authorBuilder.add("author", Json.createObjectBuilder()
                    .add("name", entry.getKey())
                    .add("manhws", entry.getValue()));

            authorsBuilder.add(authorBuilder);
        }

        // Строим окончательный JSON объект
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("mangalib", Json.createObjectBuilder().add("authors", authorsBuilder))
                .build();

        // Устанавливаем параметры JsonWriterFactory для читабельного вывода
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);

        // Записываем JSON объект в файл
        try (OutputStream os = new FileOutputStream(jsonPath)) {
            JsonWriter jsonWriter = writerFactory.createWriter(os);
            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
