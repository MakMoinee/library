package com.github.MakMoinee.library.services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequestQue {
    private static SingletonRequestQue instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private SingletonRequestQue(Context context) {
        ctx = context.getApplicationContext();
        requestQueue = Volley.newRequestQueue(ctx);
    }

    public static synchronized SingletonRequestQue getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonRequestQue(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
