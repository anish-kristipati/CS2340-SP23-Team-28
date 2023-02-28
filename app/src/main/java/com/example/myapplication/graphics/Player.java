package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Canvas;

public class Player extends Rectangle {
    private Sprite sprite;

    public Player(Context context, double x, double y, Sprite sprite) {
        super(context, x, y, sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
    }


    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, left, top);
    }

    public void moveUp() {
        top -= 160;
    }

    public void moveDown() {
        top += 160;
    }

    public void moveLeft() {
        left -= 160;
    }

    public void moveRight() {
        left += 160;
    }
}