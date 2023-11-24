package ru.vyatsu.service.converters;


import ru.vyatsu.service.Parser;
import ru.vyatsu.service.ParserFactory;
import ru.vyatsu.service.interfaces.Operation;
import ru.vyatsu.service.structure.json.MangalibJson;
import ru.vyatsu.service.structure.xml.Mangalib;

import java.io.IOException;

public class ConvertXmlToJson implements Operation {
    private Parser<MangalibJson> jsonParser;
    private Parser<Mangalib> xmlParser;
    private String inputPath;
    private String outputPath;

    public ConvertXmlToJson(String inputPath, String outputPath) {
        this.xmlParser = ParserFactory.createXmlParser();
        this.jsonParser = ParserFactory.createJsonParser();
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }
    public void convert() throws IOException {
        Mangalib mangalibXml = xmlParser.getReader().read(this.inputPath);
        MangalibJson mangalibJson = MangalibConverter.convertMangalibToMangalibJson(mangalibXml);
        jsonParser.getWriter().write(mangalibJson, this.outputPath);
    }

}
