package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vyatsu.fileconverter.Main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MainTests {

    @Test
    void testMainWithInsufficientArgs(@TempDir Path tempDir) throws IllegalArgumentException {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        try {
            assertThatThrownBy(() -> Main.main(new String[]{"data.json"}))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Необходимо указать два аргумента");
        } finally {
            System.setErr(System.err);
        }
    }
    @Test
    void testMainWithCorrectArgs(@TempDir Path tempDir) throws IOException {
        val outputPath = tempDir.resolve("test.json").toString();
        val outputFile = new File(outputPath);

        try {
            Main.main(new String[]{getClass().getClassLoader().getResource("data.xml").getPath(), outputPath});

            assertThat(outputFile.exists()).as("Выходной файл не был создан.").isTrue();
        } finally {
            if (outputFile.exists() && !outputFile.delete()) {
                throw new IOException("Не удалось удалить выходной файл после тестирования.");
            }
            System.setErr(System.err);
        }
    }

    @Test
    void testMainWithInvalidInputFile(@TempDir Path tempDir) throws IOException {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            assertThatThrownBy(() -> Main.main(new String[]{"FileIsNo.xml", "output.json"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Файл не найден");
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

            assertThatThrownBy(() -> Main.main(new String[]{invalidXmlPath.toString(), "output.json"}))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Ошибка при чтении xml файла");

        } finally {
            System.setErr(System.err);
        }
    }
}
