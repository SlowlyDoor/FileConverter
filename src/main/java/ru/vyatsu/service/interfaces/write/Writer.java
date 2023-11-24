package ru.vyatsu.service.interfaces.write;

import java.io.IOException;

public interface Writer<T> {
    void write(T data, String path) throws IOException;
}
