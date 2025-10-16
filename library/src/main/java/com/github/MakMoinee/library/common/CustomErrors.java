package com.github.MakMoinee.library.common;

import android.util.Log;

public class CustomErrors {
    public static <T> void error(T any) {
        if (any == null) {
            Log.e("CustomErrors", "Unknown error occurred.");
            return;
        }

        if (any instanceof Exception e) {
            Log.e("CustomErrors", e.getLocalizedMessage());
        } else if (any instanceof String s) {
            Log.e("CustomErrors", s);
        } else {
            Log.e("CustomErrors", any.toString());
        }
    }
}
