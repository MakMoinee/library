package com.github.MakMoinee.library.interfaces;

public interface DefaultEventListener {
     void onClickListener();

    default void onClickListener(int position) {
        /***
         * Implement your own
         */
    }

    default void onDoubleClickListener() {
        /***
         * Implement your own
         */
    }

    default void onDoubleClickListener(int position) {
        /***
         * Implement your own
         */
    }

}
