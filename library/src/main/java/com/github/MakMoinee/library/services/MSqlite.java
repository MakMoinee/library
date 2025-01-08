package com.github.MakMoinee.library.services;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MSqlite extends Service {


    private final IBinder binder = new LocalBinder();
    private MSqliteDBHelper dbHelper;

    public MSqlite(String dbName) {
        dbHelper = new MSqliteDBHelper(this, dbName);
    }

    public MSqlite() {
        this.dbHelper = new MSqliteDBHelper(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public MSqlite getService() {
            return MSqlite.this;
        }
    }

    // Insert record
    public long insertRecord(String tableName, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.insert(tableName, null, values);
    }

    // Update record
    public int updateRecord(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.update(tableName, values, whereClause, whereArgs);
    }

    // Delete record
    public int deleteRecord(String tableName, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(tableName, whereClause, whereArgs);
    }

    // Get all records
    public List<Map<String, String>> getAllRecords(String tableName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Map<String, String>> records = new ArrayList<>();

        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    record.put(cursor.getColumnName(i), cursor.getString(i));
                }
                records.add(record);
            }
            cursor.close();
        }
        return records;
    }
}
