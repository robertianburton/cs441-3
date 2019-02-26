package com.example.breakout;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class PlatformSprite {
    private Bitmap image;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int x = 100, y = (int)(Double.valueOf(screenHeight)*0.8);
    private int xd = 2, yd = 0;

    public PlatformSprite(Bitmap bmp) {
        image = Bitmap.createScaledBitmap(bmp, 400, 100, true);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        y += yd;
        x += xd;
        if( (x > screenWidth - image.getWidth())|| (x < 0)) {
            xd = xd * -1;
        }

    }
}
