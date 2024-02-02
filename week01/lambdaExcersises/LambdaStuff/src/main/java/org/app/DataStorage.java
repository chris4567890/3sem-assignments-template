package org.app;
//was added as part from the task
public interface DataStorage<T> {
    //from the task itself
    String store(T data);
    T retrive(String source);

}
