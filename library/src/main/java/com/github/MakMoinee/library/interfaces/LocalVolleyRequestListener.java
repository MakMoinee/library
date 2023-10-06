package com.github.MakMoinee.library.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

public interface LocalVolleyRequestListener {

    default void onSuccessString(String response) {
        /***
         * Default implementation
         */
    }

    default void onSuccessJSON(JSONObject object) {
        /***
         * Default implementation
         */
    }

    default void onSuccessJSONArray(JSONArray array){

    }

    void onError(Error error);
}
