package ru.vyatsu.service.interfaces;

import ru.vyatsu.service.converters.ConvertJsonToXml;
import ru.vyatsu.service.converters.ConvertXmlToJson;

public class OperationFactory {
    public static Operation createOperation(OperationType operationType, String inputPath, String outputPath) {
        switch (operationType) {
            case JSON_TO_XML:
                return new ConvertJsonToXml(inputPath, outputPath);
            case XML_TO_JSON:
                return new ConvertXmlToJson(inputPath, outputPath);
            default:
                throw new IllegalArgumentException("Неподдерживаемый тип операции");
        }
    }
}
