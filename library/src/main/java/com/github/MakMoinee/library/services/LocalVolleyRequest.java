package com.github.MakMoinee.library.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.MakMoinee.library.interfaces.LocalVolleyRequestListener;
import com.github.MakMoinee.library.models.LocalVolleyRequestBody;

import org.json.JSONArray;
import org.json.JSONObject;

public class LocalVolleyRequest {
    Context mContext;
    Boolean useSingleton = false;

    public LocalVolleyRequest(Context mContext) {
        useSingleton = false;
        this.mContext = mContext;
    }

    public LocalVolleyRequest(Context mContext, Boolean isSingle) {
        this.mContext = mContext;
        useSingleton = true;
    }

    private RequestQueue setRequestQue(Context mContext) {
        RequestQueue resultQueue = null;
        if (useSingleton) {
           resultQueue =  SingletonRequestQue.getInstance(mContext).getRequestQueue();
        } else {
            resultQueue = Volley.newRequestQueue(mContext);
        }

        return resultQueue;
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

        RequestQueue queue = this.setRequestQue(mContext);
        queue.add(jsonObjectRequest);
    }

    public void sendJSONGetRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener, RetryPolicy policy) {
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
        }) {
            @Override
            public RetryPolicy getRetryPolicy() {
                return policy;
            }
        };

        RequestQueue queue = this.setRequestQue(mContext);
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

        RequestQueue queue = this.setRequestQue(mContext);
        queue.add(arrayRequest);
    }

    public void sendJSONPostRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener) {
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

        RequestQueue queue = this.setRequestQue(mContext);
        queue.add(objectRequest);
    }

    /***
     *
     * @param body - LocalVolleyRequestBody
     * @param listener - Listener to listen for requests
     * @param policy - RetryPolicy - Build your own custom retry policy
     */
    public void sendJSONPostRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener, RetryPolicy policy) {
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
        }) {
            @Override
            public RetryPolicy getRetryPolicy() {
                return policy;
            }
        };

        RequestQueue queue = this.setRequestQue(mContext);
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

        RequestQueue queue = this.setRequestQue(mContext);
        queue.add(objectRequest);
    }

    public void sendJSONPutRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener, RetryPolicy policy) {
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
        }) {
            @Override
            public RetryPolicy getRetryPolicy() {
                return policy;
            }
        };

        RequestQueue queue = this.setRequestQue(mContext);
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
        RequestQueue queue = this.setRequestQue(mContext);
        queue.add(multipartRequest);
    }


    public void sendTextPlainRequest(LocalVolleyRequestBody body, LocalVolleyRequestListener listener) {
        StringRequest request = new StringRequest(Request.Method.GET, body.getUrl(), listener::onSuccessString, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(new Error(error.getLocalizedMessage()));
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "text/plain";
            }
        };

        RequestQueue queue = this.setRequestQue(mContext);
        queue.add(request);
    }

}
