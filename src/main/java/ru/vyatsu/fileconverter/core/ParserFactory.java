package ru.vyatsu.fileconverter.core;

import ru.vyatsu.fileconverter.core.implementation.JsonReader;
import ru.vyatsu.fileconverter.core.implementation.JsonWriter;
import ru.vyatsu.fileconverter.core.implementation.XmlReader;
import ru.vyatsu.fileconverter.core.implementation.XmlWriter;
import ru.vyatsu.fileconverter.core.model.json.MangalibJson;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;

public final class ParserFactory {

    private ParserFactory() {}

    public static Parser<MangalibXml> createXmlParser() {
        Parser<MangalibXml> parser = new Parser<>();
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
