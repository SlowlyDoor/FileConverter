package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import ru.vyatsu.fileconverter.Main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
class MainTests {

    @Test
    void testMainWithInsufficientArgs(CapturedOutput output) {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            Main.main(new String[]{"data.json"});
            // Проверка лога
            assertThat(output).contains("Необходимо указать два аргумента");
        } finally {
            System.setErr(System.err);
        }
    }

    @Test
    void testMainWithCorrectArgs(@TempDir Path tempDir) throws IOException {
        val outputPath = tempDir.resolve("test.json").toString();
        val outputFile = new File(outputPath);
        URL resourceUrl = getClass().getClassLoader().getResource("data.xml");

        try {
            if (resourceUrl != null) {
                Main.main(new String[]{resourceUrl.getPath(), outputPath});
            } else {
                // Обработка ситуации, когда ресурс не найден
                throw new RuntimeException("Ресурс не найден");
            }

            assertThat(outputFile.exists()).as("Выходной файл не был создан.").isTrue();
        } finally {
            if (outputFile.exists() && !outputFile.delete()) {
                throw new IOException("Не удалось удалить выходной файл после тестирования.");
            }
            System.setErr(System.err);
        }
    }

    @Test
    void testMainWithInvalidInputFile(CapturedOutput output) {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            Main.main(new String[]{"FileIsNo.xml", "output.json"});
            // Проверка лога
            assertThat(output).contains("Файл не найден");
        } finally {
            System.setErr(System.err);
        }
    }

    @Test
    void testMainWithConversionError(@TempDir Path tempDir, CapturedOutput output) throws IOException {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            // Подготавливаем файл с некорректными данными
            val invalidXmlPath = tempDir.resolve("invalid_data.xml");
            Files.writeString(invalidXmlPath, "invalid xml data");

            Main.main(new String[]{invalidXmlPath.toString(), "output.json"});

            // Проверка лога
            assertThat(output).contains("Ошибка при чтении xml файла");
        } finally {
            System.setErr(System.err);
        }
    }
}
