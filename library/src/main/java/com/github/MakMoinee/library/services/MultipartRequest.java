package com.github.MakMoinee.library.services;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;


import org.apache.hc.client5.http.entity.mime.ByteArrayBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.core5.http.ContentType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MultipartRequest extends Request<String> {

    private final String mBoundary;
    private Response.Listener<String> mListener;
    private Response.ErrorListener mErrorListener;
    private final Map<String, Object> mParams;


    public MultipartRequest(int method, String url, Map<String, Object> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mParams = params;
        this.mListener = listener;
        this.mErrorListener = errorListener;
        this.mBoundary = "-" + System.currentTimeMillis();
    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data;boundary=" + mBoundary;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String s = new String(response.data, StandardCharsets.UTF_8);
        return Response.success(s, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        this.mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        this.mErrorListener.onErrorResponse(error);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            // Create multipart entity builder with the boundary and mode
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setBoundary(mBoundary);
            entityBuilder.setMode(HttpMultipartMode.EXTENDED);

            // Add string params to the builder
            for (Map.Entry<String, Object> entry : mParams.entrySet()) {
                if (!(entry.getValue() instanceof byte[])) {
                    entityBuilder.addPart(entry.getKey(),
                            new StringBody(entry.getValue().toString(), ContentType.TEXT_PLAIN));
                }
            }

            // Add binary params to the builder
            for (Map.Entry<String, Object> entry : mParams.entrySet()) {
                if (entry.getValue() instanceof byte[]) {
                    entityBuilder.addPart(entry.getKey(),
                            new ByteArrayBody((byte[]) entry.getValue(),
                                    ContentType.IMAGE_JPEG,
                                    "image.jpg"));
                }
            }

            // Write the multipart entity content to the output stream
            entityBuilder.build().writeTo(outputStream);
        } catch (IOException e) {
            throw new AuthFailureError("IOException writing to ByteArrayOutputStream", e);
        }

        return outputStream.toByteArray();
    }
}

