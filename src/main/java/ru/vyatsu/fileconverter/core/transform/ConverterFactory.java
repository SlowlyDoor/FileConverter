package ru.vyatsu.fileconverter.core.transform;

import ru.vyatsu.fileconverter.core.specification.OperationType;


public class ConverterFactory {
    private ConverterFactory(){}
    public static DataConverter choice(OperationType operationType, String inputPath, String outputPath) {
        return switch (operationType) {
            case JSON_TO_XML -> new JsonToXml(inputPath, outputPath);
            case XML_TO_JSON -> new XmlToJson(inputPath, outputPath);
        };
    }
}
