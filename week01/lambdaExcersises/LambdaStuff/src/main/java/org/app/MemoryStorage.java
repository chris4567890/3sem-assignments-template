package org.app;
//also inspired from the documentation here 'https://github.com/dat3Cph/material/blob/sem2024spring/flowJavaDD/javaDD/Java8DeepDive2.md' generic classes and interfaces
import java.util.ArrayList;
import java.util.List;

public class MemoryStorage<T> implements DataStorage <T>  {
    List<T> content = new ArrayList<>();
    @Override
    public String store(T data) {
        content.add(data);
        return null;
    }

    @Override
    public T retrive(String source) {
        for(T t: content){
            if(content.contains(t)){
                return (T) source;
            }
        }
        return null;
    }
}
