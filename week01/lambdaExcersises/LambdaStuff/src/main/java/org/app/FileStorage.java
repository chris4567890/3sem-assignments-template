package org.app;

import java.io.*;
//largely inspired by the example from here 'https://github.com/dat3Cph/material/blob/sem2024spring/flowJavaDD/javaDD/SerializeObjects.md'
public class FileStorage<T> implements DataStorage<T> {
    //private T content;

    @Override
    public String store(T data) {

        String filename = data.toString();
        String fileSuffix = (java.time.LocalDateTime.now()).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        filename = filename + fileSuffix+".ser";
        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(data);
            out.close();
            fos.close();
            return filename;
        }catch (Exception e){
            e.printStackTrace();
        }
        return filename;
    }

    @Override
    public T retrive(String source) {
        try {
            FileInputStream fis = new FileInputStream(source);
            ObjectInputStream in = new ObjectInputStream(fis);
            T content = (T) in.readObject();
            in.close();
            fis.close();
            return content;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
