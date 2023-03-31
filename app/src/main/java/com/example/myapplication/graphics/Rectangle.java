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

    public boolean obstacleIntersect(Rectangle other){ // TODO make vehicle class
        return !(this.left > other.getRight() || this.right < other.getLeft()
                || this.top != other.getTop() || this.bottom != other.getBottom());
    }
    public boolean intersects(Rectangle other) {
        //System.out.println(other.getRight());
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
