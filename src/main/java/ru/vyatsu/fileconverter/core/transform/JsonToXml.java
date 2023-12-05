package ru.vyatsu.fileconverter.core.transform;

import ru.vyatsu.fileconverter.core.ApplicationException;

public class JsonToXml extends DataConverter {

    public JsonToXml(String inputPath, String outputPath) {
        super(inputPath,outputPath);
    }

    public void convert() throws ApplicationException {
        xmlParser.getWriter().write(
                MangalibJsonConverter.mangalibJsonToMangalib(
                        jsonParser.getReader().read(this.inputPath)
                ),
                this.outputPath
        );
    }
}
