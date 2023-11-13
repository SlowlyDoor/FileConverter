package ru.vyatsu;

import ru.vyatsu.service.converters.ConvertJsonToXml;
import ru.vyatsu.service.converters.ConvertXmlToJson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            String userDir = System.getProperty("user.dir");
            String path = userDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
            //String path = "C:\\Users\\hellp\\IdeaProjects\\untitled1\\src\\main\\resources\\";

            try {
                // Обрабатываем имя первого файла
                String inputFile = args[0];
                String outputFile = args[1];

                // Проверяем, есть ли расширение у имени первого файла
                if (!inputFile.contains(".")) {
                    // Читаем содержимое файла, чтобы определить его тип
                    String fileContent = readFileContent(path + inputFile);

                    // Определяем тип файла на основе его содержимого
                    String inputExtension = determineFileType(fileContent);
                    if (inputExtension == null) {
                        System.out.println("Ошибка! Не удалось определить тип файла.");
                        return;
                    }

                    inputFile = inputFile + inputExtension;
                }

                // Проверяем, есть ли расширение у имени второго файла
                if (!outputFile.contains(".")) {
                    // Определяем тип входного файла на основе его расширения
                    String inputExtension = inputFile.substring(inputFile.lastIndexOf("."));

                    // Определяем противоположный тип для выходного файла
                    String outputExtension = determineOppositeFileType(inputExtension);
                    if (outputExtension == null) {
                        System.out.println("Конвертер предусматривает преобразование из xml в json, либо из json в xml. Иные расширения недоступны");
                        return;
                    }

                    outputFile = outputFile + outputExtension;
                }

                // Получаем расширения входного и выходного файлов
                String inputExtension = inputFile.substring(inputFile.lastIndexOf("."));
                String outputExtension = outputFile.substring(outputFile.lastIndexOf("."));

                if (inputExtension.equals(".xml") && outputExtension.equals(".json")) {
                    ConvertXmlToJson conv = new ConvertXmlToJson();
                    conv.Converter(path + inputFile, path + outputFile);
                    System.out.println("Преобразование выполнено успешно!");
                } else if (inputExtension.equals(".json") && outputExtension.equals(".xml")) {
                    ConvertJsonToXml conv = new ConvertJsonToXml();
                    conv.Convert(path + inputFile, path + outputFile);
                    System.out.println("Преобразование выполнено успешно!");
                } else {
                    System.out.println("Конвертер предусматривает преобразование из xml в json, либо из json в xml. Иные расширения недоступны");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ошибка! Команда должна содержать 2 аргумента: <исходный файл> <конвертированный файл>");
        }
    }

    private static String readFileContent(String fileName) throws IOException {
        File file = new File(fileName);

        // Проверяем, существует ли файл без расширения, и если да, пытаемся найти подходящее расширение
        if (!file.exists() && !fileName.contains(".")) {
            File dir = file.getAbsoluteFile().getParentFile();
            String nameWithoutExtension = file.getName();
            String[] extensionsToTry = {".xml", ".json"};

            for (String extension : extensionsToTry) {
                File potentialFile = new File(dir, nameWithoutExtension + extension);
                if (potentialFile.exists()) {
                    fileName = potentialFile.getAbsolutePath();
                    break;
                }
            }
        }

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

    private static String determineOppositeFileType(String inputExtension) {
        if (inputExtension.equals(".xml")) {
            return ".json";
        } else if (inputExtension.equals(".json")) {
            return ".xml";
        }
        return null;
    }
}
