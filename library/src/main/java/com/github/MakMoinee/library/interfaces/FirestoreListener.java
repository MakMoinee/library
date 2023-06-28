package com.github.MakMoinee.library.interfaces;

public interface FirestoreListener {
    <T> void onSuccess(T any);
    void onError(Error error);
}
