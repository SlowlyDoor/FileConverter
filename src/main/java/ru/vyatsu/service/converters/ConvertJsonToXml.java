package ru.vyatsu.service.converters;


import ru.vyatsu.service.Parser;
import ru.vyatsu.service.ParserFactory;
import ru.vyatsu.service.interfaces.Operation;
import ru.vyatsu.service.structure.json.MangalibJson;
import ru.vyatsu.service.structure.xml.Mangalib;

import java.io.IOException;

public class ConvertJsonToXml implements Operation {

    private Parser<MangalibJson> jsonParser;
    private Parser<Mangalib> xmlParser;
    private String inputPath;
    private String outputPath;

    public ConvertJsonToXml(String inputPath, String outputPath) {
        this.jsonParser = ParserFactory.createJsonParser();
        this.xmlParser = ParserFactory.createXmlParser();
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void convert() throws IOException {
        MangalibJson mangalibJson = jsonParser.getReader().read(this.inputPath);
        Mangalib mangalibXml = MangalibJsonConverter.convertMangalibJsonToMangalib(mangalibJson);
        xmlParser.getWriter().write(mangalibXml, this.outputPath);
    }
}
