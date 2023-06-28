package com.github.MakMoinee.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.MakMoinee.library.common.MapForm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapForm.convertObjectToMap(null);
    }
}