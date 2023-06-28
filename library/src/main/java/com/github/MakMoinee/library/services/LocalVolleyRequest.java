package com.github.MakMoinee.library.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.MakMoinee.library.interfaces.LocalVolleyRequestListener;
import com.github.MakMoinee.library.models.LocalVolleyRequestBody;

import org.json.JSONObject;

public class LocalVolleyRequest {
    Context mContext;

    public LocalVolleyRequest(Context mContext) {
        this.mContext = mContext;
    }

    public void sendJSONGetRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, body.getUrl(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onSuccessJSON(response);
            }
        }, error -> {
            try {
                if (error != null) {
                    listener.onError(new Error(error.getLocalizedMessage()));
                }
            } catch (Exception e) {
                listener.onError(new Error("unknown error"));
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(jsonObjectRequest);
    }
}
