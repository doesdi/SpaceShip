package com.example.idontnow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable{

    private Thread thread;
    private boolean isPlaying;
    private int scrX,scrY;
    private Paint paint;
    private float x=430,y=1600;
    private float scrRatioX,scrRatioY;
    public static boolean drag = false;
    private List<Bullet> bullets;
    private Rocket rocket;
    private GameView gameView;
    private Background background1,background2;


    public GameView(Context context, int scrX, int scrY) {
        super(context);
        this.scrX= scrX;
        this.scrY= scrY;
        scrRatioX = 1080f/scrX;
        scrRatioY = 1920f/scrY;

        background1 = new Background(scrX,scrY,getResources());
        background2 = new Background(scrX,scrY,getResources());

        rocket = new Rocket(this,scrY,getResources());

        bullets = new ArrayList<>();

        background2.y = scrY;
        paint = new Paint();
    }
    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }
    }
    private  void  update(){
        background2.y += 10 * scrRatioY;
        background1.y += 10 * scrRatioY;

        if(background1.y + background1.background.getHeight() < 0){
            background1.y = scrY;
        }
        if(background2.y + background2.background.getHeight() < 0){
            background2.y = scrY;
        }
        rocket.x = (int)x;
        rocket.y = (int)y;

       // List<Bullet> trash = new ArrayList<>();
      //  for (Bullet bullet : bullets){
       //     if(bullet.y > scrY)
       //         trash.add(bullet);
      //      bullet.y += 50 * scrRatioY;
      //  }
      //   for(Bullet bullet : trash)
       //    bullets.remove(bullet);


    }
    private  void  draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background,background2.x,background2.y,paint);

            canvas.drawBitmap(rocket.rocket, rocket.x, rocket.y,null);

           // Bullet bullet = new Bullet(getResources());
           // bullet.x = rocket.x - rocket.Width+200;
           // bullet.y = rocket.y - rocket.Height+200;
          //  while(drag) {
           //     bullets.add(bullet);
           //         canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
          //  }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    private  void  sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
    public void pause(){
        try {
            isPlaying=false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    float dragX = 0;
    float dragY = 0;
    public boolean onTouchEvent(MotionEvent rocket) {
        float evX = rocket.getX();
        float evY = rocket.getY();
        switch (rocket.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (evX >= x && evX <= rocket.getX() && evY >= y && evY <= rocket.getY()) {
                    drag = true;
                    dragX = evX - x;
                    dragY = evY - y;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (drag) {
                    x = evX - dragX;
                    y = evY - dragY;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                drag = false;
                break;
        }
        return true;
    }


}
