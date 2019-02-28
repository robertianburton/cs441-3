package com.example.breakout;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.breakout.MainThread.canvas;

//private MainThread thread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    enum States {
        Running, GameOver, GameWin
    }
    States state;
    private MainThread thread;
    private PlatformSprite platformSprite;
    private BackgroundSprite backgroundSprite, overScreen, winScreen;
    private BallSprite ballSprite;
    private ArrayList<BrickSprite> bricks;

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
        overScreen = new BackgroundSprite(BitmapFactory.decodeResource(getResources(), R.drawable.gameoverbg));
        winScreen = new BackgroundSprite(BitmapFactory.decodeResource(getResources(), R.drawable.gamewinbg));
        ballSprite = new BallSprite(BitmapFactory.decodeResource(getResources(), R.drawable.fire));
        bricks = new ArrayList<>();

        thread.setRunning(true);
        thread.start();
        state = States.Running;

        for(int j = 0; j <6; j++) {
            for(int i = 0; i < 7; i++) {
                BrickSprite newBrickSprite = new BrickSprite(BitmapFactory.decodeResource(getResources(), R.drawable.ice), 100+235*i, 50+j*80, 180, 60);
                bricks.add(newBrickSprite);
            }
        }


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
        if(state==States.Running) {
            for(int i = 0; i < bricks.size(); i++) {
                int dir = ballSprite.intersect(bricks.get(i));
                if(dir > 0) {
                    System.out.println("Collision: "+ dir);
                    bricks.remove(i);
                    break;
                }
            }
            int q = ballSprite.intersect(platformSprite);
            platformSprite.update();
            int check = ballSprite.update();
            if(check>0) {
                state=States.GameOver;
            }
            if(bricks.size()==0) {
                state=States.GameWin;
            }
        } else if(state==States.GameOver) {
            canvas.drawColor(Color.RED);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null && state==States.Running) {
            canvas.drawColor(Color.GREEN);

            backgroundSprite.draw(canvas);


            /*Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 255));
            canvas.drawRect(400, 400, 300, 300, paint);*/

            platformSprite.draw(canvas);

            ballSprite.draw(canvas);

            for(int i = 0; i < bricks.size(); i++) {
                bricks.get(i).draw(canvas);
            }
            //System.out.println("hi");
        } else if(canvas!=null && state==States.GameOver) {
            overScreen.draw(canvas);
        } else if(canvas!=null && state==States.GameWin) {
            winScreen.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if(event.getAction() == MotionEvent.ACTION_UP) {
            System.out.println("Touch at "+x+","+y);
            platformSprite.movePlatform(x);
        }


        return true;
    }
}
