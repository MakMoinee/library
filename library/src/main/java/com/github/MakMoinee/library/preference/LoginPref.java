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
}
