package com.github.MakMoinee.library.configs;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetMyProperty {
    public static Properties getConfigProperties(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            Log.e("getMyProperty_err1", e.getLocalizedMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e("getMyProperty_err2", e.getLocalizedMessage());
            }
        }
        return properties;
    }


    /**
     * @param fieldName - Name of the field you're trying to get the configuration
     * @param inputStream - Create input stream to open the properties file (e.g: getAssets().open("local.properties");)
     * @return String
     */
    public static String getStringConfig(String fieldName, InputStream inputStream) {
        Properties properties = GetMyProperty.getConfigProperties(inputStream);
        // Access specific properties
        String result = properties.getProperty(fieldName);
        return result;
    }
}
