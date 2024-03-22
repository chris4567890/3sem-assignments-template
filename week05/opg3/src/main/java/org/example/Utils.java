package org.example;

import org.example.config.ApiException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String getPropertyValue(String general_kenobi, String helloThere){
        try(InputStream is = Utils.class.getClassLoader().getResourceAsStream(helloThere)) {
            Properties prop = new Properties();
            prop.load(is);
            return prop.getProperty(general_kenobi);

        }catch(IOException ex){
            ex.printStackTrace();
            throw new ApiException(500,"could not read sorry to bother you");
        }
    }

}
