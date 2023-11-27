package ru.vyatsu.fileconverter.core.transform;

import java.io.IOException;

public class JsonToXml extends DataConverter {

    public JsonToXml(String inputPath, String outputPath) {
        super(inputPath,outputPath);
    }

    public void convert() throws IOException {
        xmlParser.getWriter().write(
                MangalibJsonConverter.mangalibJsonToMangalib(
                        jsonParser.getReader().read(this.inputPath)
                ),
                this.outputPath
        );
    }
}
