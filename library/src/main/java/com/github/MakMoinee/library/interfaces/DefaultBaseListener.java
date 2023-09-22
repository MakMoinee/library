package com.github.MakMoinee.library.interfaces;

public interface DefaultBaseListener {
    <T> void onSuccess(T any);
    void onError(Error error);
}
