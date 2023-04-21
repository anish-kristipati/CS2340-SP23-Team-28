package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Canvas;

public class SpriteRectangle extends Rectangle {
    private final Sprite sprite;
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

    public void drawBackground(Canvas canvas) {
        sprite.drawBackground(canvas);
    }

    //TODO Change method name
    //TODO refactor image background name
    public void draw1(Canvas canvas) {
        sprite.draw1(canvas, left, top, resizeX, resizeY);
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
        yLevel++;
    }

    public void moveDown() {
        top += 160;
        yLevel--;
    }

    public void moveLeft() {
        left -= shift;
        right -= shift;
    }

    public void moveRight() {
        //System.out.println("adding " + shift);
        left += shift;
        right += shift;
    }

    public void moveRight(int dist) {
        left += dist;
        right += dist;
    }

    public void setLeft(int location) {
        this.left = location;
    }

    public void setRight(int location) {
        this.right = location;
    }

    public void setTop(int location) {
        this.top = location;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public String toString() { // for debugging
        return ("left, right, bottom, top: " + this.left + " " + this.right + " " + this.bottom
            + " " + this.top);
    }
}