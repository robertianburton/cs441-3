package com.example.breakout;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import static com.example.breakout.MainThread.canvas;

//private MainThread thread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private PlatformSprite platformSprite;
    private BackgroundSprite backgroundSprite;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        platformSprite = new PlatformSprite(BitmapFactory.decodeResource(getResources(), R.drawable.platform));
        backgroundSprite = new BackgroundSprite(BitmapFactory.decodeResource(getResources(), R.drawable.mountainbg));

        thread.setRunning(true);
        thread.start();



    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true; //used if you need to retry stopping the surface
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        platformSprite.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            canvas.drawColor(Color.GREEN);

            backgroundSprite.draw(canvas);

            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 255));
            canvas.drawRect(400, 400, 300, 300, paint);

            platformSprite.draw(canvas);
        }
    }
}
