package ru.vyatsu.fileconverter.core;

import lombok.Getter;
import lombok.Setter;
import ru.vyatsu.fileconverter.core.specification.Reader;
import ru.vyatsu.fileconverter.core.specification.Writer;

@Getter
@Setter
public final class Parser<T> {
    private Writer<T> writer;
    private Reader<T> reader;
}