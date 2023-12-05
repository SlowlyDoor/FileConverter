package ru.vyatsu.fileconverter;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import ru.vyatsu.fileconverter.core.ApplicationException;
import ru.vyatsu.fileconverter.core.transform.ConverterFactory;
import ru.vyatsu.fileconverter.core.specification.OperationType;

import java.util.Objects;

@Slf4j
public class Main {

    public static void main(String[] args) {
        try {
            processConversion(args);
            log.info("Преобразование успешно выполнено!");
        } catch (ArrayIndexOutOfBoundsException indexOutException) {
            log.error("Необходимо указать два аргумента!", indexOutException);
        } catch (Exception exception) {
            log.error("Произошла ошибка: " + exception.getMessage(), exception);
        }
    }

    private static void processConversion(String[] args) throws ApplicationException {
        PropertyConfigurator.configure(Objects
                .requireNonNull(Main.class.getClassLoader()
                        .getResource("log4j.properties")).getPath());
        ConverterFactory.choice(OperationType.getOperationType(args[0], args[1]),
                                args[0], args[1]).convert();
    }
}
