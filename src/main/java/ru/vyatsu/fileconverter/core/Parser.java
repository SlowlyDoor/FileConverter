package ru.vyatsu.fileconverter.core;

import ru.vyatsu.fileconverter.core.specification.Reader;
import ru.vyatsu.fileconverter.core.specification.Writer;


public final class Parser<T> {
    private Writer<T> writer;
    private Reader<T> reader;

    public Writer<T> getWriter() {
        return writer;
    }

    public Reader<T> getReader() {
        return reader;
    }

    public void setWriter(Writer<T> writer) {
        this.writer = writer;
    }

    public void setReader(Reader<T> reader) {
        this.reader = reader;
    }
}