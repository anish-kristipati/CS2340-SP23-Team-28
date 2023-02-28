package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Canvas;


public abstract class Rectangle {
    protected double left;
    protected double right;
    protected double top;
    protected double bottom;

    public Rectangle(Context context, double x, double y, double w, double h) {
        left = x;
        right = x + w;
        top = y;
        bottom = y + h;

    }

    boolean intersects(Rectangle other) {
        return !(this.left > other.getRight() || this.right < other.getLeft()
            || this.top > other.getBottom() || this.bottom < other.getTop());
    }

    public abstract void draw(Canvas canvas);

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getTop() {
        return top;
    }

    public double getBottom() {
        return bottom;
    }
}
