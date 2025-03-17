package com.github.MakMoinee.library.services;

import android.content.Context;
import android.util.Log;

import com.github.MakMoinee.library.interfaces.DefaultBaseListener;
import com.github.MakMoinee.library.interfaces.DefaultEventListener;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class LocalAndroidServer extends NanoHTTPD {
    private static final String TAG = "LocalServer";
    private Context context;

    DefaultBaseListener listener;

    public LocalAndroidServer(int port, DefaultBaseListener l) throws IOException {
        super(port);
        this.listener = l;
        start();
    }

    @Override
    public Response serve(IHTTPSession session) {
        String clientIp = session.getHeaders().get("remote-addr"); // Get client IP
        Log.d(TAG, "Request received from IP: " + clientIp);

        if (session.getMethod() == Method.POST) {
            try {
                session.parseBody(null);
            } catch (IOException | ResponseException e) {

                Log.d(TAG, "error: " + e.getLocalizedMessage());
            }
            Map<String, String> params = session.getParms();
            String message = params.get("message");

            if ("detect".equals(message)) {
                // Log detection and show notification
                Log.d(TAG, "Detection Alert received from: " + clientIp);
                listener.onSuccess("detected");
            }
        }

        return newFixedLengthResponse("Received");
    }
}
