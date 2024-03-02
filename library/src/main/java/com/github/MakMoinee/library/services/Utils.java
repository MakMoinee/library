package com.github.MakMoinee.library.services;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static boolean isAlphaNumeric(String s) {
        return s.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$");
    }

    public static String getCurrentDate(String pattern) {
        Date currentDateTime = new Date();

        if(pattern.isEmpty()){
            pattern = "yyyy-MM-dd hh:mm a";
        }

        // Define the format pattern for date and time in 12-hour format with AM/PM
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(pattern);

        // Format the date and time
        String formattedDateTime = dateTimeFormat.format(currentDateTime);

        return formattedDateTime;
    }

    /**
     *
     * @param title
     * @param body
     * @param token
     */
    public void sendMessage(String title, String body, String token){
        // Obtain the FirebaseMessaging instance
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();

        // Create a data payload for the FCM message
        Map<String, String> data = new HashMap<>();
        data.put("title", title);
        data.put("body", body);

        // Construct the FCM message
        RemoteMessage.Builder messageBuilder = new RemoteMessage.Builder(token);
        messageBuilder.setData(data);

        // Send the FCM message
        firebaseMessaging.send(messageBuilder.build());
    }
}
