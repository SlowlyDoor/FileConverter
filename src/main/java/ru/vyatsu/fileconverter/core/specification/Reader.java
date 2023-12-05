package ru.vyatsu.fileconverter.core.specification;

import ru.vyatsu.fileconverter.core.ApplicationException;

public interface Reader<T> {

    T read(String path) throws ApplicationException;
}
