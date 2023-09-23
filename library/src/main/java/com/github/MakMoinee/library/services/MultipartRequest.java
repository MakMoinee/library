package com.github.MakMoinee.library.services;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class MultipartRequest extends StringRequest {
    private String boundary;
    private final String lineEnd = "\r\n";
    private final String twoHyphens = "--";

    private final String jsonBody;
    private final Map<String, String> headers;

    public MultipartRequest(
            int method,
            String url,
            String jsonBody,
            Response.Listener<String> listener,
            Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.jsonBody = jsonBody;
        this.boundary = generateBoundary();
        headers = new HashMap<>();
        headers.put("Content-Type", "multipart/form-data;boundary=" + boundary);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }


    private String generateBoundary() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return "----" + bytesToHex(bytes);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            // Add JSON data as a part of the multipart request
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"json_data\"" + lineEnd);
            dos.writeBytes("Content-Type: application/json" + lineEnd + lineEnd);
            dos.write(jsonBody.getBytes("utf-8"));
            dos.writeBytes(lineEnd);

            // Add any additional parts here if needed

            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonResponse = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonResponse,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }
}
