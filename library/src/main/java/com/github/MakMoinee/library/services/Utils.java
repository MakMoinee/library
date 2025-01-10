package com.github.MakMoinee.library.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.github.MakMoinee.library.models.FirestoreRequestBody;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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

        if (pattern.isEmpty()) {
            pattern = "yyyy-MM-dd hh:mm a";
        }

        // Define the format pattern for date and time in 12-hour format with AM/PM
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(pattern);

        // Format the date and time
        String formattedDateTime = dateTimeFormat.format(currentDateTime);

        return formattedDateTime;
    }

    /**
     * @param title
     * @param body
     * @param token
     */
    public static void sendMessage(String title, String body, String token) {
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

    public static FirestoreRequestBody createBody(Map<String, Object> data) {
        FirestoreRequestBody body = new FirestoreRequestBody.FirestoreRequestBodyBuilder()
                .setParams(data)
                .build();
        return body;
    }

    /**
     * @param navView
     * @return view used for accessing inner views like textView, editText and many more
     */
    public static View getNavView(NavigationView navView) {
        return navView.getHeaderView(0);
    }

    public static void setUpNavigation(Activity activity, NavigationView navView, NavController navController, AppBarConfiguration mAppBarConfiguration) {
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) activity, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
