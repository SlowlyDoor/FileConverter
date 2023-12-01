package ru.vyatsu.fileconverter.core.specification;

import ru.vyatsu.fileconverter.core.ApplicationException;

public interface Writer<T> {
    void write(T data, String path) throws ApplicationException;
}
