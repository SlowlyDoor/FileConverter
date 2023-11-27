package ru.vyatsu.fileconverter.core.transform;

import java.io.IOException;

public class XmlToJson extends DataConverter {
    public XmlToJson(String inputPath, String outputPath) {
        super(inputPath,outputPath);
    }
    public void convert() throws IOException {
        jsonParser.getWriter().write(
                MangalibConverter.mangalibToMangalibJson(
                        xmlParser.getReader().read(this.inputPath)
                ),
                this.outputPath
        );
    }

}
