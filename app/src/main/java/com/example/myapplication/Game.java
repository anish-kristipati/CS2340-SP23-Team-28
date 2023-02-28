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
    private Player player;

    private SpriteSheet spriteSheet;
    private int difficulty;
    private int lives;

    public Game(Context context) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.gameLoop = new GameLoop(this, surfaceHolder);
        spriteSheet = new SpriteSheet(context);
        this.setFocusable(true);

    }

    public void setSprite(String character) {
        spriteSheet.setBitmap(character);
        this.player = new Player(getContext(), 640, 2770, spriteSheet.getPlayerSprite());
    }

    public void setCharacterData(int lives, int difficulty) {
        this.lives = lives;
        this.difficulty = difficulty;
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
        player.draw(canvas);
        drawUPS(canvas);
        invalidate();
    }

    public void drawUPS(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        paint.setTextSize(100);
        canvas.drawText("Lives: " + lives + "\nDifficulty: " + difficulty, 100, 320, paint);
    }


    public Player getPlayer() {
        return player;

    }
}