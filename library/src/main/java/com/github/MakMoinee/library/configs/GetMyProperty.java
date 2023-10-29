package com.github.MakMoinee.library.configs;

import android.content.Context;
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
     *
     * @param fieldName - Name of the field you're trying to get
     * @param configFileName - Name of the config file (e.g: local.properties)
     * @param mContext - Context (e.g: this, requireActivity() in fragment)
     * @return
     */
    public static String getStringConfig(String fieldName,String configFileName, Context mContext) {
        InputStream inputStream = null;
        String result = "";
        try {
            inputStream = mContext.getAssets().open(configFileName);
            Properties properties = GetMyProperty.getConfigProperties(inputStream);
            // Access specific properties
             result = properties.getProperty(fieldName);
        } catch (IOException e) {
            Log.e("getStringConfig_err", e.getLocalizedMessage());
            result = "";
        }

        return result;
    }
}
