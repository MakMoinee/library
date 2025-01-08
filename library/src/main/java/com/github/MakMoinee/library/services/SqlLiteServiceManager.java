package com.github.MakMoinee.library.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class SqlLiteServiceManager {
    private static SqlLiteServiceManager instance;
    private static MSqlite sqliteService;
    private boolean isBound = false;

    private SqlLiteServiceManager() {}


    public static synchronized SqlLiteServiceManager getInstance(Context context, MSqlite s) {
        if (instance == null) {
            instance = new SqlLiteServiceManager();
            sqliteService = s;
            instance.bindService(context);
        }
        return instance;
    }

    private void bindService(Context context) {
        Intent intent = new Intent(context, MSqlite.class);
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MSqlite.LocalBinder binder = (MSqlite.LocalBinder) service;
            sqliteService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public MSqlite getSQLiteService() {
        if (isBound) {
            return sqliteService;
        } else {
            return null;
        }
    }
}
