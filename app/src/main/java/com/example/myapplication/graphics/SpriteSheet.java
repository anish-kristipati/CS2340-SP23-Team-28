package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.myapplication.R;

public class SpriteSheet {
    private Bitmap bitmap;
    private Bitmap background;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        //TODO ADD CHANGE TO IMAGE!
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_shoe);
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.temp_background_5);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap getBackground() {
        return background;
    }

    public Sprite getPlayerSprite() {
        return new Sprite(this, new Rect(0,0, bitmap.getWidth(),bitmap.getHeight()));
    }
}
