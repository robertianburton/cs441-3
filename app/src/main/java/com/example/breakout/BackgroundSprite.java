package com.example.breakout;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BackgroundSprite {
    private Bitmap image;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


    public BackgroundSprite(Bitmap bmp) {
        image = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, null);
    }

    public void update() {
    }
}
