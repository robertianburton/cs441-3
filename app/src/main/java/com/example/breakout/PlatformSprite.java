package com.example.breakout;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PlatformSprite {
    private Bitmap image;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int x = 100;
    private int y = (int)(Double.valueOf(screenHeight)*0.96);


    private int w = 300;
    private int h = 80;

    public PlatformSprite(Bitmap bmp) {
        image = Bitmap.createScaledBitmap(bmp, w, h, true);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        /*y += yd;
        x += xd;
        if( (x > screenWidth - image.getWidth())|| (x < 0)) {
            xd = xd * -1;
        }*/
    }

    public void movePlatform(int x) {
        this.x = max(0,min(x-(w/2),screenWidth-w));
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
