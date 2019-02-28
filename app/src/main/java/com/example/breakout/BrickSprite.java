package com.example.breakout;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BrickSprite {
    private Bitmap image;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int x = 0;
    private int y = 0;



    private int w;
    private int h;

    public BrickSprite(Bitmap bmp, int x, int y, int newWidth, int newHeight) {
        w = newWidth;
        h = newHeight;
        this.x = x;
        this.y = y;
        image = Bitmap.createScaledBitmap(bmp, w, h, true);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
