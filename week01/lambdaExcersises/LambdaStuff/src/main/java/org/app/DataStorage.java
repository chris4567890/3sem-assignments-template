package org.app;

public interface DataStorage<T> {

    String store(T data);
    T retrive(String source);

}
