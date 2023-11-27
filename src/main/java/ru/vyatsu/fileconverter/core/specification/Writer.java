package ru.vyatsu.fileconverter.core.specification;

import java.io.IOException;

public interface Writer<T> {
    void write(T data, String path) throws IOException;
}
