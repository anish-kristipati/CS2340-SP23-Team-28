package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.myapplication.graphics.Player;
import com.example.myapplication.graphics.Sprite;
import com.example.myapplication.graphics.SpriteSheet;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private final Player player;

    private SpriteSheet spriteSheet;
    public Game(Context context) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.gameLoop = new GameLoop(this, surfaceHolder);
        spriteSheet = new SpriteSheet(context);
        this.player = new Player(getContext(), 640, 2770, spriteSheet.getPlayerSprite());

        this.setFocusable(true);

    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_MOVE:

            return true;
        case MotionEvent.ACTION_UP:
        }

        return super.onTouchEvent(event);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        player.draw(canvas);
        invalidate();
    }

    public void drawUPS(Canvas canvas) {
        String updatesPerSecond = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        paint.setTextSize(50);
        canvas.drawText("UPS: " + updatesPerSecond, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String framesPerSecond = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        paint.setTextSize(50);
        canvas.drawText("FPS: " + framesPerSecond, 100, 200, paint);
    }

    public Player getPlayer() {
        return player;

    }
}