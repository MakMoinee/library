package com.github.MakMoinee.library.services;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat();

        // Format the date and time
        String formattedDateTime = dateTimeFormat.format(pattern);

        return formattedDateTime;
    }
}
