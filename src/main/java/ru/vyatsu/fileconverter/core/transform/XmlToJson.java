package ru.vyatsu.fileconverter.core.transform;

import ru.vyatsu.fileconverter.core.ApplicationException;

public class XmlToJson extends DataConverter {

    public XmlToJson(String inputPath, String outputPath) {
        super(inputPath,outputPath);
    }

    public void convert() throws ApplicationException {
        jsonParser.getWriter().write(
                MangalibConverter.mangalibToMangalibJson(
                        xmlParser.getReader().read(this.inputPath)
                ),
                this.outputPath
        );
    }

}
