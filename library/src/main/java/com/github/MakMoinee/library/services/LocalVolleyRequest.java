package com.github.MakMoinee.library.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.MakMoinee.library.interfaces.LocalVolleyRequestListener;
import com.github.MakMoinee.library.models.LocalVolleyRequestBody;

import org.json.JSONArray;
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

    public void sendJSONArrayGetRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener) {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, body.getUrl(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listener.onSuccessJSONArray(response);
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
        queue.add(arrayRequest);
    }

    public void sendJSONPostGetRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, body.getUrl(), body.getBody(), response -> {
            if (response.length() > 0) {
                listener.onSuccessJSON(response);
            } else {
                listener.onError(new Error("empty response"));
            }
        }, error -> {
            try {
                listener.onError(new Error(error.getLocalizedMessage()));
            } catch (Exception e) {
                listener.onError(new Error("empty error, please check internet connectivity"));
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(objectRequest);
    }

    public void sendJSONPutRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.PUT, body.getUrl(), body.getBody(), response -> {
            if (response.length() > 0) {
                listener.onSuccessJSON(response);
            } else {
                listener.onError(new Error("empty response"));
            }
        }, error -> {
            try {
                listener.onError(new Error(error.getLocalizedMessage()));
            } catch (Exception e) {
                listener.onError(new Error("empty error, please check internet connectivity"));
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(objectRequest);
    }

    public void sendMultipartJSONPostRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener) {
        // Create a new request with the appropriate URL
        String url = body.getUrl();
        MultipartRequest multipartRequest = new MultipartRequest(Request.Method.POST, url, body.getBodyMap(), response -> {
            if (response.length() > 0) {
                listener.onSuccessString(response);
            } else {
                listener.onError(new Error("empty response"));
            }
        }, error -> {
            try {
                listener.onError(new Error(error.getLocalizedMessage()));
            } catch (Exception e) {
                listener.onError(new Error("empty error, please check internet connectivity"));
            }
        });

        // Create a RequestQueue and add the multipart request to it
        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(multipartRequest);
    }

}
