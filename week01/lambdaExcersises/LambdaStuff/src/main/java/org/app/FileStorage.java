package org.app;

import java.io.*;

public class FileStorage<T> implements DataStorage<T> {
    private T content;

    @Override
    public String store(T data) {
        this.content = data;
        String filename = content.toString();
        String fileSuffix = (java.time.LocalDateTime.now()).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        filename = filename + fileSuffix+".ser";
        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(content);
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
