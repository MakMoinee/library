package com.github.MakMoinee.library.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

public class MyDialog extends ProgressDialog {
    public MyDialog(Context context) {
        super(context);
        setDefault();
    }

    private void setDefault() {
        this.setMessage("Sending Request");
        this.setCancelable(false);
    }

    public void setCustomMessage(String message){
        this.setMessage(message);
        this.setCancelable(false);
    }
}
