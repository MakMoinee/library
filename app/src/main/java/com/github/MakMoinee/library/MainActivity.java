package com.github.MakMoinee.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.MakMoinee.library.common.MapForm;
import com.github.MakMoinee.library.interfaces.DefaultEventListener;
import com.github.MakMoinee.library.interfaces.FirestoreListener;
import com.github.MakMoinee.library.models.FirestoreRequestBody;
import com.github.MakMoinee.library.services.FirestoreRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapForm.convertObjectToMap(null);
        FirestoreRequest fs = new FirestoreRequest();
        FirestoreRequestBody body = new FirestoreRequestBody.FirestoreRequestBodyBuilder()
                .build();
        fs.findAll(body, new FirestoreListener() {
            @Override
            public <T> void onSuccess(T any) {

            }

            @Override
            public void onError(Error error) {

            }
        });

        DefaultEventListener listener = new DefaultEventListener() {
            @Override
            public void onClickListener() {

            }
        };
    }
}