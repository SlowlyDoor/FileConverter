package ru.vyatsu.service.interfaces.read;

import java.io.IOException;

public interface Reader<T> {
    T read(String path) throws IOException;
}
