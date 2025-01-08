package com.github.MakMoinee.library.common;


import android.content.ContentValues;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapForm {
    public static Map<String, Object> convertObjectToMap(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> param = mapper.convertValue(data, Map.class);
        return param;
    }

    public static ContentValues toContentValues(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        ContentValues contentValues = new ContentValues();
        try {
            // Convert object to a Map
            Map<String, Object> map = mapper.convertValue(data, Map.class);

            // Populate ContentValues with the map
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof String) {
                    contentValues.put(key, (String) value);
                } else if (value instanceof Integer) {
                    contentValues.put(key, (Integer) value);
                } else if (value instanceof Long) {
                    contentValues.put(key, (Long) value);
                } else if (value instanceof Float) {
                    contentValues.put(key, (Float) value);
                } else if (value instanceof Double) {
                    contentValues.put(key, (Double) value);
                } else if (value == null) {
                    contentValues.putNull(key);
                }
            }
        } catch (Exception e) {
            Log.e("MapForm::error", e.getLocalizedMessage());
            return null;
        }
        return contentValues;
    }
}
