package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.myapplication.R;

public class SpriteSheet {
    private Bitmap bitmap;
    private final Bitmap background;

    private final Context context;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_shoe);
        background =
            BitmapFactory.decodeResource(context.getResources(), R.drawable.temp_background_5);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(String character) {
        switch (character) {
        case "Monopoly Man":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_man);
            break;
        case "Dawg":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_dog);
            break;
        case "Shoe":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_shoe);
            break;
        case "motorcycle":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.motorcycle);
            break;
        case "blueCar":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluecar);
            break;
        case "truck":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.truck);
            break;
        default:
            System.out.println("Whoops");
            break;
        }

    }

    public Bitmap getBackground() {
        return background;
    }

    public Sprite getSprite() {
        return new Sprite(this, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
    }
}
