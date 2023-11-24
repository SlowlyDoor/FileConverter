package ru.vyatsu.service;

import ru.vyatsu.service.interfaces.JsonReader;
import ru.vyatsu.service.interfaces.JsonWriter;
import ru.vyatsu.service.interfaces.XmlReader;
import ru.vyatsu.service.interfaces.XmlWriter;
import ru.vyatsu.service.structure.xml.Mangalib;
import ru.vyatsu.service.structure.json.MangalibJson;

public final class ParserFactory {
    public static Parser<Mangalib> createXmlParser() {
        Parser<Mangalib> parser = new Parser<>();
        parser.setReader(new XmlReader());
        parser.setWriter(new XmlWriter());
        return parser;
    }

    public static Parser<MangalibJson> createJsonParser() {
        Parser<MangalibJson> parser = new Parser<>();
        parser.setReader(new JsonReader());
        parser.setWriter(new JsonWriter());
        return parser;
    }
}
