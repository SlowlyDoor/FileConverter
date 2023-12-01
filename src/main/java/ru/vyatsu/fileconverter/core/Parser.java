package ru.vyatsu.fileconverter.core;

import lombok.Data;
import ru.vyatsu.fileconverter.core.specification.Reader;
import ru.vyatsu.fileconverter.core.specification.Writer;

@Data
public final class Parser<T> {
    private Writer<T> writer;
    private Reader<T> reader;
}