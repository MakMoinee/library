package com.github.MakMoinee.library.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.MakMoinee.library.interfaces.RealtimeDbListener;
import com.github.MakMoinee.library.models.RealtimeDBBody;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealtimeDbRequest {

    FirebaseDatabase db;
    DatabaseReference dbRef;

    public RealtimeDbRequest() {
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference();
    }


    public DatabaseReference getDbRef() {
        return dbRef;
    }

    public void addValueEventListener(ValueEventListener eventListener) {
        this.dbRef.addValueEventListener(eventListener);
    }

    public void findAll(RealtimeDBBody body, ChildEventListener childEventListener) {
        this.dbRef.child(body.getChildName()).orderByKey().addChildEventListener(childEventListener);
    }

    public void find(RealtimeDBBody body, RealtimeDbListener listener) {
        this.dbRef
                .child(body.getChildName())
                .child(body.getKey())
                .get()
                .addOnSuccessListener(dataSnapshot -> listener.onSuccess(dataSnapshot))
                .addOnFailureListener(e -> listener.onError(new Error(e.getMessage())));
    }

    public void insertOnly(RealtimeDBBody body, RealtimeDbListener listener) {
        String id = this.dbRef.child(body.getChildName()).push().getKey();
        this.dbRef.child(body.getChildName()).child(id).setValue(body.getParams(), (error, ref) -> {
            if (error != null) {
                listener.onError(new Error(error.getMessage()));
            } else {
                listener.onSuccess(id);
            }
        });
    }

    public void delete(RealtimeDBBody body, RealtimeDbListener listener) {
        this.dbRef.child(body.getChildName()).child(body.getKey()).removeValue((error, ref) -> {
            if (error != null) {
                listener.onError(new Error(error.getMessage()));
            } else {
                listener.onSuccess(null);
            }
        });
    }

    public void updateOnly(RealtimeDBBody body, RealtimeDbListener listener) {
        this.dbRef
                .child(body.getChildName())
                .child(body.getKey())
                .setValue(body.getParams())
                .addOnSuccessListener(unused -> listener.onSuccess(null))
                .addOnFailureListener(e -> listener.onError(new Error(e.getMessage())));
    }


}
