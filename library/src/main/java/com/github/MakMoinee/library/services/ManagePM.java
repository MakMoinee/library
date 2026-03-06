package com.github.MakMoinee.library.services;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class ManagePM {
    public static String getStringConfig(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            Bundle bundle = ai.metaData;

            return bundle.getString(key);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e("managePM_err", Objects.requireNonNull(e.getLocalizedMessage()));
            return "";
        }
    }

    public static int getIntConfig(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            Bundle bundle = ai.metaData;

            return bundle.getInt(key);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e("managePM_err", Objects.requireNonNull(e.getLocalizedMessage()));
            return 0;
        }
    }

    public static Double getDoubleConfig(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            Bundle bundle = ai.metaData;

            return bundle.getDouble(key);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e("managePM_err", Objects.requireNonNull(e.getLocalizedMessage()));
            return 0.0;
        }
    }

    public static Boolean getBoolConfig(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            Bundle bundle = ai.metaData;

            return bundle.getBoolean(key);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e("managePM_err", Objects.requireNonNull(e.getLocalizedMessage()));
            return false;
        }
    }
}
