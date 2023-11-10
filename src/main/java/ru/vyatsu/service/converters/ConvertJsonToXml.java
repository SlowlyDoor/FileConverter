package ru.vyatsu.service.converters;

import ru.vyatsu.service.structure.Mangalib;
import ru.vyatsu.service.structure.Manhwa;
import ru.vyatsu.service.structure.TeamTranslation;

import javax.json.*;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConvertJsonToXml {
    public void Convert(String json, String xml)  {
        Mangalib mangalib = ReadJson(json);
        WriteXml(xml, mangalib);
    }

    private void WriteXml(String path, Mangalib mangalib) {
        try {
            JAXBContext context = JAXBContext.newInstance(Mangalib.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(mangalib, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private Mangalib ReadJson(String path) {
        try {
            FileInputStream inputStream = new FileInputStream(path);

            JsonParserFactory factory = Json.createParserFactory(null);
            JsonParser parser = factory.createParser(inputStream, StandardCharsets.UTF_8);

            if (!parser.hasNext() && parser.next() != JsonParser.Event.START_OBJECT) return null;

            Mangalib mangalib = new Mangalib();
            List<Manhwa> manhwaList = new ArrayList<>();

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                if (event == JsonParser.Event.KEY_NAME && parser.getString().equals("authors")) {
                    event = parser.next();
                    if (event == JsonParser.Event.START_ARRAY) {
                        JsonArray authorsArray = parser.getArray();
                        for (JsonValue authorValue : authorsArray) {
                            JsonObject authorObject = authorValue.asJsonObject();
                            if (authorObject.containsKey("author")) {
                                JsonObject author = authorObject.getJsonObject("author");

                                if (author.containsKey("name") && author.containsKey("manhws")) {
                                    String authorName = author.getString("name");
                                    JsonArray manhwsArray = author.getJsonArray("manhws");

                                    for (int i = 0; i < manhwsArray.size(); i++) {
                                        JsonObject manhwaObject = manhwsArray.getJsonObject(i);
                                        Manhwa manhwa = new Manhwa();

                                        manhwa.setId(String.valueOf(manhwaList.size()));
                                        manhwa.setAuthor(authorName);
                                        manhwa.setTitle(manhwaObject.getString("title"));
                                        manhwa.setPublicationYear(manhwaObject.getInt("publicationYear"));
                                        manhwa.setChapters(manhwaObject.getInt("chapters"));

                                        if (manhwaObject.containsKey("translationTeam")) {
                                            JsonObject translationTeamObject = manhwaObject.getJsonObject("translationTeam");
                                            TeamTranslation translationTeam = new TeamTranslation();
                                            translationTeam.setName(translationTeamObject.getString("name"));
                                            translationTeam.setPublicationProjects(translationTeamObject.getInt("publicationProjects"));
                                            translationTeam.setAdminName(translationTeamObject.getString("adminName"));
                                            manhwa.setTranslationTeam(translationTeam);
                                        }

                                        manhwaList.add(manhwa);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            mangalib.setMangalib(manhwaList);
            return mangalib;
        } catch (FileNotFoundException e) {
            // Обработка или логирование исключения по необходимости
            e.printStackTrace();
            return null;
        }
    }
}
