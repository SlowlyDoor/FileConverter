package ru.vyatsu.service;

import ru.vyatsu.service.interfaces.XmlWriter;
import ru.vyatsu.service.interfaces.read.Reader;
import ru.vyatsu.service.interfaces.write.Writer;


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