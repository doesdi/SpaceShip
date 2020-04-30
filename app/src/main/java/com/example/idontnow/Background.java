package com.example.idontnow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {

    int x=0,y=0;
    Bitmap background;
    Background(int scrX,int scrY, Resources res){
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background,scrX,scrY,false);

    }
}
