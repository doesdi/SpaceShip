package com.example.idontnow;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;



public class Rocket {


        Bitmap rocket;
        Paint p;
        int Width,Height,x,y;
        boolean drag = false;
        Rocket (GameView gameView, int scrY,Resources res){

            p = new Paint();
            rocket = BitmapFactory.decodeResource(res,R.drawable.ship1);
            Width = rocket.getWidth();
            Height = rocket.getHeight();

            Width /=6;
            Height /=6;

            rocket = Bitmap.createScaledBitmap(rocket,Width,Height,false);
            x= 430;
            y= 1600;

        }





}

