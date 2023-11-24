package ru.vyatsu.service.interfaces;

public enum OperationType {
    JSON_TO_XML,
    XML_TO_JSON;

    public static OperationType getOperationType(String inputFile, String outputFile) {
        String inputExtension = getFileExtension(inputFile);
        String outputExtension = getFileExtension(outputFile);

        if (inputExtension.equals("json") && outputExtension.equals("xml")) {
            return JSON_TO_XML;
        } else if (inputExtension.equals("xml") && outputExtension.equals("json")) {
            return XML_TO_JSON;
        } else {
            throw new IllegalArgumentException("Неподдерживаемый тип операции");
        }
    }

    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("Файл без расширения: " + fileName);
        }
        return fileName.substring(lastDotIndex + 1);
    }
}
