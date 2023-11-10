package ru.vyatsu;

import ru.vyatsu.service.converters.ConvertJsonToXml;
import ru.vyatsu.service.converters.ConvertXmlToJson;
import ru.vyatsu.service.structure.Mangalib;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main
{
    public static void main(String[] args) throws JAXBException, FileNotFoundException, IOException {
        if (args.length == 2) {
            String path = "..\\..\\..\\src\\main\\resources\\" ;

            // Проверка, если входной файл задан без расширения
            if (!args[0].contains(".")) {
                String fileContent = readFileContent(args[0]);

                // Определение типа файла на основе его содержимого
                String inputExtension = determineFileType(fileContent);
                if (inputExtension == null) {
                    System.out.println("Ошибка! Не удалось определить тип файла.");
                    return;
                }

                args[0] = args[0] + inputExtension;
            }

            // Проверка, если выходной файл задан без расширения
            if (!args[1].contains(".")) {
                // Определение типа файла на основе расширения входного файла
                String inputExtension = args[0].substring(args[0].lastIndexOf("."));
                if (inputExtension.equals(".xml")) {
                    args[1] = args[1] + ".json";
                } else if (inputExtension.equals(".json")) {
                    args[1] = args[1] + ".xml";
                } else {
                    System.out.println("Конвертер предусматривает преобразование из xml в json, либо из json в xml. Иные расширения недоступны");
                    return;
                }
            }

            // Получение расширений входного и выходного файлов
            String inputExtension = args[0].substring(args[0].lastIndexOf("."));
            String outputExtension = args[1].substring(args[1].lastIndexOf("."));

            if (inputExtension.equals(".xml") && outputExtension.equals(".json")) {
                ConvertXmlToJson conv = new ConvertXmlToJson();
                conv.Converter(path + args[0],path + args[1]);
                System.out.println("Преобразование выполнено успешно!");
            } else if (inputExtension.equals(".json") && outputExtension.equals(".xml")) {
                ConvertJsonToXml conv = new ConvertJsonToXml();
                conv.Convert(path + args[0],path + args[1]);
                System.out.println("Преобразование выполнено успешно!");
            } else {
                System.out.println("Конвертер предусматривает преобразование из xml в json, либо из json в xml. Иные расширения недоступны");
            }
        } else {
            System.out.println("Ошибка! Команда должна содержать 2 аргумента: <исходный файл> <конвертированный файл>");
        }
    }


    private static String readFileContent(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }

    private static String determineFileType(String fileContent) {
        if (fileContent.startsWith("{")) {
            return ".json";
        } else if (fileContent.startsWith("<")) {
            return ".xml";
        }
        return null;
    }

}