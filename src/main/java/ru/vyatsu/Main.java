package ru.vyatsu;

import ru.vyatsu.service.Parser;
import ru.vyatsu.service.ParserFactory;
import ru.vyatsu.service.converters.MangalibConverter;
import ru.vyatsu.service.converters.MangalibJsonConverter;
import ru.vyatsu.service.interfaces.Operation;
import ru.vyatsu.service.interfaces.OperationFactory;
import ru.vyatsu.service.interfaces.OperationType;
import ru.vyatsu.service.structure.json.MangalibJson;
import ru.vyatsu.service.structure.xml.Mangalib;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        try {
            OperationType operationType = OperationType.getOperationType(args[0], args[1]);
            Operation operation = OperationFactory.createOperation(operationType, args[0], args[1]);
            operation.convert();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.warning("Необходимо указать два аргумента!");
        } catch (Exception e) {
            logger.warning("Произошла ошибка: " + e.getMessage());
        }
    }
}