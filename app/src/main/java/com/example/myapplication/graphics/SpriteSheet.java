package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.myapplication.R;

public class SpriteSheet {
    private Bitmap bitmap;
    private final Bitmap background;
    private BitmapFactory.Options bitmapOptions;
    private final Context context;

    public SpriteSheet(Context context) {
        bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_shoe);
        background =
            BitmapFactory.decodeResource(context.getResources(), R.drawable.background_no_logs);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(String character) {
        switch (character) {
        case "Monopoly Man":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_man,
                bitmapOptions);
            break;
        case "Dawg":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_dog,
                bitmapOptions);
            break;
        case "Shoe":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopoly_shoe,
                bitmapOptions);
            break;
        case "motorcycle":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.motorcycle,
                bitmapOptions);
            break;
        case "blueCar":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluecar,
                bitmapOptions);
            break;
        case "truck":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.truck,
                bitmapOptions);
            break;
        case "small_log":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.small_log,
                bitmapOptions);
            break;
        case "big_log":
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.big_log,
                bitmapOptions);
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

    public int getWidth() {
        return bitmap.getWidth();
    }
}
