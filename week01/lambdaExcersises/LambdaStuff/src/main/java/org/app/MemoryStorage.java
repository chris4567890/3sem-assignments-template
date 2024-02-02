package org.app;

public class MemoryStorage<T> implements DataStorage <T>  {
    private T content;
    @Override
    public String store(T data) {
        this.content = data;
        return null;
    }

    @Override
    public T retrive(String source) {
        return content;
    }
}
