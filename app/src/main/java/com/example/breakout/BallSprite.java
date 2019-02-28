package com.example.breakout;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class BallSprite {
    private Bitmap image;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int x = 0, y = screenHeight/4*3, w = 60, h = 60;
    private int xd = 3, yd = -3;
    Random rand;

    public BallSprite(Bitmap bmp) {
        image = Bitmap.createScaledBitmap(bmp, w, h, true);
        Random rand = new Random();
        x = rand.nextInt(screenWidth);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public int update() {
        y += yd;
        x += xd;

        if( (x > screenWidth - image.getWidth())|| (x < 0)) {
            xd = xd * -1;
        }
        if(y < 0) {
            yd = yd * -1;
        }
        if(y > screenHeight - image.getHeight()) {
            return 1;
        }
        return 0;
    }

    public int intersect(BrickSprite brick) {
        int by = brick.getY();
        int bx = brick.getX();
        int bw = brick.getW();
        int bh = brick.getH();

        if(y+h>by && y+h<by+bh && x+w>bx && x<bx+bw && y < by && yd > 0) {
            //top
            yd = yd * -1;
            y-=yd+1;
            System.out.println("Top Collision");
            return 1;
        }
        if(x+w>bx+bw && x<bx+bw && x>bx && y+h>by && y<by+bh && xd<0) {
            //Right
            xd = xd * -1;
            x+=xd+1;
            System.out.println("Right Collision");
            return 2;
        }
        if(y<by+bh && y+h>by+bh && x+w>bx && x<bx+bw && y > by && yd < 0) {
            //bottom
            System.out.println("Bottom Collision");
            yd = yd * -1;
            y+=yd+1;
            return 3;
        }
        if(x<bx && x+w>bx && y+h>by && y<by+bh && x+w<bx+bw && xd>0) {
            //Left
            xd = xd * -1;
            x-=xd+1;
            System.out.println("Left Collision");
            return 4;
        }
        return 0;
    }

    public int intersect(PlatformSprite platform) {
        int by = platform.getY();
        int bx = platform.getX();
        int bw = platform.getW();
        int bh = platform.getH();

        if(y+h>by && y+h<by+bh && x+w>bx && x<bx+bw && y < by) {
            //top
            yd = yd * -1;
            y = (int)(Double.valueOf(screenHeight)*0.96)-h-1;
            yd--;
            if(xd<0) {
                xd = xd - 1;
            } else if (xd>0) {
                xd = xd + 1;
            }
            int r = new Random().nextInt(1);
            if(r==1) {
                xd = xd * -1;
            }
            System.out.println("Top Collision");

            return 1;
        }
        if(x+w>bx+bw && x<bx+bw && x>bx && y+h>by+2 && y<by+bh && xd<0) {
            //Right
            xd = 0;
            x++;
            System.out.println("Right Collision");
            return 2;
        }
        if(y<by+bh && y+h>by+bh && x+w>bx && x<bx+bw && y > by && yd < 0) {
            //bottom
            System.out.println("Bottom Collision");
            yd = 0;
            y++;
            return 3;
        }
        if(x<bx && x+w>bx && y+h>by+2 && y<by+bh && x+w<bx+bw && xd>0) {
            //Left
            xd = 0;
            x--;
            System.out.println("Left Collision");
            return 4;
        }
        return 0;
    }
}
