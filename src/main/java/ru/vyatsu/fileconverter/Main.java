package ru.vyatsu.fileconverter;

import org.apache.log4j.PropertyConfigurator;
import ru.vyatsu.fileconverter.core.transform.ConverterFactory;
import ru.vyatsu.fileconverter.core.specification.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            processConversion(args);
            LOGGER.info("Преобразование успешно выполнено!");
        } catch (ArrayIndexOutOfBoundsException indexOutException) {
            LOGGER.error("Необходимо указать два аргумента!", indexOutException);
        } catch (Exception exception) {
            LOGGER.error("Произошла ошибка: " + exception.getMessage(), exception);
        }
    }

    private static void processConversion(String[] args) throws IOException {
        PropertyConfigurator.configure(
                Objects.requireNonNull(
                        Main.class.getClassLoader().getResource("log4j.properties")
                ).getPath()
        );
        ConverterFactory.choice(
                OperationType.getOperationType(args[0], args[1]),
                args[0],
                args[1]
        ).convert();
    }
}
