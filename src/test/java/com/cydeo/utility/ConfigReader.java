package com.cydeo.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // Declare properties object at class level so that it can be accessible in methods
    private static Properties properties = new Properties();

    // Load the file once
    static {
        try {
            FileInputStream in = new FileInputStream("config.properties");
            properties.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
    *   Get the value of the key according to the argument passed
    *   @param
    *   @return the value of the key provided
     */
    public static String confRead(String key) {
        return properties.getProperty(key);
    }
}

// ToDo: Rework driver utility to use the utility from the config file to load a driver
