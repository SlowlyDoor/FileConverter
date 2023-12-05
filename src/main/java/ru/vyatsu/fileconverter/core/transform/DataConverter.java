package ru.vyatsu.fileconverter.core.transform;

import ru.vyatsu.fileconverter.core.ApplicationException;
import ru.vyatsu.fileconverter.core.Parser;
import ru.vyatsu.fileconverter.core.ParserFactory;
import ru.vyatsu.fileconverter.core.model.json.MangalibJson;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;

public abstract class DataConverter {

    protected Parser<MangalibJson> jsonParser;
    protected Parser<MangalibXml> xmlParser;
    protected String inputPath;
    protected String outputPath;

    protected DataConverter(String inputPath, String outputPath) {
        this.jsonParser = ParserFactory.createJsonParser();
        this.xmlParser = ParserFactory.createXmlParser();
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public abstract void convert() throws ApplicationException;
}
