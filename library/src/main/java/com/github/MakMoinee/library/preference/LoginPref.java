package com.github.MakMoinee.library.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class LoginPref {
    Context mContext;
    SharedPreferences pref;

    public LoginPref(Context mContext) {
        this.mContext = mContext;
        this.pref = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);
    }


    public void storeLogin(Map<String, Object> data) {
        if (data == null) {
            return;
        }
        SharedPreferences.Editor editor = pref.edit();
        for (Map.Entry<String, Object> obj : data.entrySet()) {
            if (obj.getValue() instanceof String) {
                editor.putString(obj.getKey(), (String) obj.getValue());
            }
            if (obj.getValue() instanceof Float) {
                editor.putFloat(obj.getKey(), (Float) obj.getValue());
            }

            if (obj.getValue() instanceof Integer) {
                editor.putInt(obj.getKey(), (int) obj.getValue());
            }

            if (obj.getValue() instanceof Boolean) {
                editor.putBoolean(obj.getKey(), (boolean) obj.getValue());
            }
        }
        editor.commit();
        editor.apply();
    }

    public Map<String, Object> getLogin() {
        return (Map<String, Object>) pref.getAll();
    }

    public void clearLogin() {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear(); // Remove all stored preferences
        editor.apply();
    }

    public String getStringItem(String fieldName) {
        Object data = this.getItem(fieldName);
        if (data instanceof String) {
            String result = (String) data;
            return result;
        }
        return null;
    }

    public Float getFloatItem(String fieldName) {
        Object data = this.getItem(fieldName);
        if (data instanceof Float) {
            Float result = (Float) data;
            return result;
        }
        return null;
    }

    public Double getDoubleItem(String fieldName) {
        Object data = this.getItem(fieldName);
        if (data instanceof Double) {
            Double result = (Double) data;
            return result;
        }
        return null;
    }

    private Object getItem(String fieldName) {
        Object data = null;

        for (Map.Entry<String, Object> obj : this.getLogin().entrySet()) {
            if (obj.getKey().equals(fieldName)) {
                data = obj.getValue();
                break;
            }
        }

        return data;
    }
}
