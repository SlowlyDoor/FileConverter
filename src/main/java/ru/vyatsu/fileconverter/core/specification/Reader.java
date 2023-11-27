package ru.vyatsu.fileconverter.core.specification;

import java.io.IOException;

public interface Reader<T> {
    T read(String path) throws IOException;
}
