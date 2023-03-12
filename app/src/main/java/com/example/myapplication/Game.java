package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.myapplication.graphics.SpriteRectangle;
import com.example.myapplication.graphics.SpriteSheet;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private SpriteRectangle player;

    private SpriteSheet spriteSheet;
    private int difficulty;
    private int lives;
    private int points;

    private double minPos;

    private final int[] pointsArray = {0, 50, 50, 50, 0, 60, 60, 60, 60, 0, 30, 30, 0, 60, 60, 0, 15, 100};
    private int pointIndex;

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
        this.player = new SpriteRectangle(getContext(), 640, 2770, spriteSheet.getPlayerSprite());
    }

    public void setCharacterData(int lives, int difficulty, int points) {
        this.lives = lives;
        this.difficulty = difficulty;
        this.points = points;
        this.minPos = player.getTop();
        this.pointIndex = 0;
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
    public void draw(Canvas canvas) {
        super.draw(canvas);
        calculatePoints(canvas);
        player.draw(canvas);
        calculatePoints(canvas);
        invalidate();
    }

    public void calculatePoints(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.yellow));
        paint.setTextSize(80);
        if(player.getTop() < minPos) {
            minPos = player.getTop();
            pointIndex++;
            points += pointsArray[pointIndex];
        }
        canvas.drawText("Points: " + points + "  Lives: " + lives + " Difficulty: " + difficulty, 100, 120, paint);
    }


    public SpriteRectangle getPlayer() {
        return player;

    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
}