package com.github.MakMoinee.library.interfaces;

import android.graphics.Bitmap;

public interface QRCodeLocal {
    byte[] generateQRCode(String text, int width, int height);
    Bitmap getBitmap();
}
