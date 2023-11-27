package ru.vyatsu.fileconverter;

import ru.vyatsu.fileconverter.core.transform.ConverterFactory;
import ru.vyatsu.fileconverter.core.specification.OperationType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            ConverterFactory.choice(
                            OperationType.getOperationType(args[0], args[1]),
                            args[0],
                            args[1])
                    .convert();

            System.out.println("Преобразование успешно выполнено!");

        } catch (ArrayIndexOutOfBoundsException e) {
            String errorMessage = "Необходимо указать два аргумента!";
            LOGGER.log(Level.SEVERE, errorMessage, e);
            e.printStackTrace();
            throw new IllegalArgumentException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = "Произошла ошибка: " + e.getMessage();
            LOGGER.log(Level.SEVERE, errorMessage, e);
            e.printStackTrace();
            throw new RuntimeException(errorMessage, e);
        }
    }
}