package com.example.idontnow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet {
    int x,y;
    Bitmap bullet;

    Bullet(Resources res){

        bullet = BitmapFactory.decodeResource(res,R.drawable.bullet);

        int width = bullet.getWidth();
        int height = bullet.getHeight();

        width/=10;
        height/=10;

        bullet = Bitmap.createScaledBitmap(bullet,width, height,false);

    }

}
