package com.example.myapplication.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;

    }

    public void draw(Canvas canvas, double x, double y, int resizeX, int resizeY) {
        drawBackground(canvas);
        canvas.drawBitmap(
            spriteSheet.getBitmap(),
            null,
            new Rect((int) x, (int) y, (int) x + resizeX, (int) y + resizeY),
            null
        );
    }

    public void draw1(Canvas canvas, double x, double y, int resizeX, int resizeY) {
        //drawBackground(canvas); TODO fix
        canvas.drawBitmap(
            spriteSheet.getBitmap(),
            null,
            new Rect((int) x, (int) y, (int) x + resizeX, (int) y + resizeY),
            null
        );
    }

    public void drawBackground(Canvas canvas) {
        canvas.drawBitmap(
            spriteSheet.getBackground(),
            null,
            new Rect(0, 0, 1440, 2920),
            null
        );
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }
}
