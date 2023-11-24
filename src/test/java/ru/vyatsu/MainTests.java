package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class MainTests {

    private static final Logger logger = LoggerFactory.getLogger(MainTests.class);

    @Test
    void testMainWithInsufficientArgs(@TempDir Path tempDir) throws IOException {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        try {
            val args = new String[]{"data.json"};
            Main.main(args);

            assertThat(errContent.toString()).contains("Необходимо указать два аргумента!");
        } finally {
            System.setErr(System.err);
        }
    }
    @Test
    void testMainWithCorrectArgs(@TempDir Path tempDir) throws IOException {
        val outputPath = tempDir.resolve("test.json").toString();
        val outputFile = new File(outputPath);

        if (outputFile.exists() && !outputFile.delete()) {
            throw new IOException("Не удалось удалить существующий выходной файл перед тестированием.");
        }

        try {
            val args = new String[]{"src/test/resources/data.xml", outputPath};
            Main.main(args);

            assertThat(outputFile.exists()).as("Выходной файл не был создан.").isTrue();
        } finally {
            if (outputFile.exists() && !outputFile.delete()) {
                throw new IOException("Не удалось удалить выходной файл после тестирования.");
            }
        }
    }

    @Test
    void testMainWithInvalidInputFile(@TempDir Path tempDir) throws IOException {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            val args = new String[]{"FileIsNo.xml", "output.json"};
            Main.main(args);

            assertThat(errContent.toString()).contains("Произошла ошибка: Файл не найден");
        } finally {
            System.setErr(System.err);
        }
    }

    @Test
    void testMainWithConversionError(@TempDir Path tempDir) throws IOException {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            // Подготавливаем файл с некорректными данными
            val invalidXmlPath = tempDir.resolve("invalid_data.xml");
            Files.write(invalidXmlPath, "invalid xml data".getBytes(StandardCharsets.UTF_8));

            val args = new String[]{invalidXmlPath.toString(), "output.json"};
            Main.main(args);

            assertThat(errContent.toString()).contains("Произошла ошибка: при чтении xml файла");
        } finally {
            System.setErr(System.err);
        }
    }
}
