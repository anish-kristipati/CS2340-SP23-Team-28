package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Canvas;

public class SpriteRectangle extends Rectangle {
    private Sprite sprite;
    private int resizeX;
    private int resizeY;
    private int shift;
    public SpriteRectangle(Context context, double x, double y, Sprite sprite) {
        super(context, x, y, sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
    }


    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, left, top, resizeX, resizeY);
    }
    //TODO Change method name
    //TODO refactor image background name
    public void draw1(Canvas canvas) {
        sprite.draw1(canvas,left,top,resizeX,resizeY);
    }
    public void setResizeXY(int resizeX, int resizeY) {
        this.resizeX = resizeX;
        this.resizeY = resizeY;
    }
    public void setShift(int shift) {
        this.shift = shift;
    }
    public void moveUp() {
        top -= 160;
    }

    public void moveDown() {
        top += 160;
    }

    public void moveLeft() {
        left -= shift;
    }

    public void moveRight() {
        left += shift;
    }

    public void setLeft(int location) {
        this.left = location;
    }
}