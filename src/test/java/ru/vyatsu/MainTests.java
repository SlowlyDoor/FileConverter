package ru.vyatsu;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class MainTests {

    @Test
    void testConvertXmlToJson() {
        Main main = new Main();

        // Создаем временные файлы для теста
        String userDir = System.getProperty("user.dir");
        String path = userDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String inputXmlPath = "data.xml";
        String outputJsonPath = "dataTest.json";

        try {
            // Запускаем конвертацию
            main.main(new String[]{inputXmlPath, outputJsonPath});

            // Проверяем, что JSON файл был создан
            assertTrue(new File(path + outputJsonPath).exists());

            // Проверяем, что созданный JSON файл совпадает с ожидаемым
            assertTrue(isFileContentEqual(path + outputJsonPath, path + "data.json"));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Удаляем временные файлы
            new File(path + outputJsonPath).delete();
        }
    }

    @Test
    void testConvertJsonToXml() {
        Main main = new Main();

        // Создаем временные файлы для теста
        String userDir = System.getProperty("user.dir");
        String path = userDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String inputJsonPath = "data.json";
        String outputXmlPath = "dataTest.xml";

        try {
            // Запускаем конвертацию
            main.main(new String[]{inputJsonPath, outputXmlPath});

            // Проверяем, что XML файл был создан
            assertTrue(new File(path + outputXmlPath).exists());

            // Проверяем, что созданный XML файл совпадает с ожидаемым
            assertTrue(isFileContentEqual(path + outputXmlPath, path + "data.xml"));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Удаляем временные файлы
            new File(path + outputXmlPath).delete();
        }
    }

    @Test
    void testConvertWithoutExtensions() {
        Main main = new Main();

        // Создаем временные файлы для теста
        String userDir = System.getProperty("user.dir");
        String path = userDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String inputJsonPath = "dataJS";
        String inputXmlPath = "dataXML";
        String output = "output";

        try {
            // Запускаем конвертацию с файлами, не содержащими расширения
            main.main(new String[]{inputJsonPath, output});
            assertTrue(new File(path + "output.xml").exists());

            main.main(new String[]{inputXmlPath, output});
            assertTrue(new File(path + "output.xml").exists());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Удаляем временные файлы
            new File(path + "output.json").delete();
            new File(path + "output.xml").delete();
        }
    }

    @Test
    void testConvertFirstMixedExtensions() {
        Main main = new Main();

        // Создаем временные файлы для теста
        String userDir = System.getProperty("user.dir");
        String path = userDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String inputXmlPath = "data.xml";
        String inputJsonPath = "data.json";
        String output = "output";

        try {
            // Запускаем конвертацию с файлами, содержащими и не содержащими расширения
            main.main(new String[]{inputXmlPath, output});
            assertTrue(new File(path + "output.json").exists());

            main.main(new String[]{inputJsonPath, output});
            assertTrue(new File(path + "output.xml").exists());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Удаляем временные файлы
            new File(path + "output.json").delete();
            new File(path + "output.xml").delete();
        }
    }

    @Test
    void testConvertSecondMixedExtensions() {
        Main main = new Main();

        // Создаем временные файлы для теста
        String userDir = System.getProperty("user.dir");
        String path = userDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String inputXmlPath = "dataXML";
        String inputJsonPath = "dataJS";
        String outputXmlPath = "output.xml";
        String outputJsonPath = "output.json";

        try {
            // Запускаем конвертацию с файлами, содержащими и не содержащими расширения
            main.main(new String[]{inputXmlPath, outputJsonPath});
            assertTrue(new File(path + "output.json").exists());

            main.main(new String[]{inputJsonPath, outputXmlPath});
            assertTrue(new File(path + "output.xml").exists());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Удаляем временные файлы
            new File(path + "output.json").delete();
            new File(path + "output.xml").delete();
        }
    }


    private boolean isFileContentEqual(String filePath1, String filePath2) {
        try {
            String content1 = Files.readString(Path.of(filePath1)).replaceAll("\\s", "");
            String content2 = Files.readString(Path.of(filePath2)).replaceAll("\\s", "");
            return content1.equals(content2);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
