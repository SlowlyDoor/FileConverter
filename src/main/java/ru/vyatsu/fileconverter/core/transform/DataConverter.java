package ru.vyatsu.fileconverter.core.transform;

import ru.vyatsu.fileconverter.core.Parser;
import ru.vyatsu.fileconverter.core.ParserFactory;
import ru.vyatsu.fileconverter.core.model.json.MangalibJson;
import ru.vyatsu.fileconverter.core.model.xml.Mangalib;

import java.io.IOException;

public abstract class DataConverter {
    protected Parser<MangalibJson> jsonParser;
    protected Parser<Mangalib> xmlParser;
    protected String inputPath;
    protected String outputPath;

    public DataConverter(String inputPath, String outputPath) {
        this.jsonParser = ParserFactory.createJsonParser();
        this.xmlParser = ParserFactory.createXmlParser();
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public abstract void convert() throws IOException;
}
