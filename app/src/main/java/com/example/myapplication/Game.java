package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import androidx.core.content.ContextCompat;

import com.example.myapplication.graphics.SpriteRectangle;
import com.example.myapplication.graphics.SpriteSheet;

import java.util.Random;

//TODO Refactor variable names and add variable speeds based on lane
// (higher lane has higher speed, lower lanes have lower speed)
// TODO make the high score variable (maxPoints) persistent
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private SpriteRectangle player;
    private SpriteRectangle dummy;
    // private Player player;
    private SpriteRectangle truck;
    private SpriteRectangle truck1;
    private SpriteRectangle car;
    private SpriteRectangle car1;
    private SpriteRectangle bike;
    private SpriteRectangle bike1;
    private SpriteRectangle small_log1, small_log2, small_log3;
    private SpriteRectangle big_log1, big_log2, big_log3;

    private final SpriteSheet spriteSheetPlayer;

    private int difficulty;
    private static int lives;
    private static int points;
    private static int maxPoints;
    private final int[] spriteData = {142, 142, 160, 426, 142, 2, 284, 142, 4, 142, 142, 6};
    //TODO ADD NEW SPRITES AND SPRITE SETTER METHODS (PRIVATE)
    private double minPos;

    private int resizeX;
    private int resizeY;


    private final int[] pointsArray =
        {0, 50, 30, 15, 0, 60, 60, 60, 60, 0, 50, 30, 0, 60, 60, 0, 15, 100};
    private int pointIndex;

    public Game(Context context, int dif) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //this.gameLoop = new GameLoop(this, surfaceHolder);
        spriteSheetPlayer = new SpriteSheet(context);
        constructObstacles(context, dif);
        this.setFocusable(true);

    }

    private int getRand(int ub){ //  [0, ub] inclusive
        Random rand = new Random();
        return rand.nextInt(ub + 1);
    }
    private void constructObstacles(Context context, int dif) {
        SpriteSheet spriteSheetTruck = new SpriteSheet(context);
        spriteSheetTruck.setBitmap("truck");

        this.truck = new SpriteRectangle(getContext(), 0, 2608, spriteSheetTruck.getSprite());
        this.truck.setResizeXY(spriteData[3], spriteData[4]);
        // this.truck.setShift(spriteData[5]);
        this.truck.setShift(2*dif + getRand(2));

        this.truck1 = new SpriteRectangle(getContext(), 0, 1176, spriteSheetTruck.getSprite());
        this.truck1.setResizeXY(spriteData[3], spriteData[4]);
        //this.truck1.setShift(spriteData[5]);
        this.truck1.setShift(2*dif + getRand(2));

        SpriteSheet spriteSheetCar = new SpriteSheet(context);
        spriteSheetCar.setBitmap("blueCar");
        this.car = new SpriteRectangle(getContext(), 1014, 2446, spriteSheetCar.getSprite());
        this.car.setResizeXY(spriteData[6], spriteData[7]);
        // this.car.setShift(spriteData[8]);
        this.car.setShift(2*dif + getRand(2));


        this.car1 = new SpriteRectangle(getContext(), 1014, 1014, spriteSheetCar.getSprite());
        this.car1.setResizeXY(spriteData[6], spriteData[7]);
        // this.car1.setShift(spriteData[8]);
        this.car1.setShift(2*dif + getRand(2));

        SpriteSheet spriteSheetBike = new SpriteSheet(context);
        spriteSheetBike.setBitmap("motorcycle");
        this.bike = new SpriteRectangle(getContext(), 0, 2284, spriteSheetBike.getSprite());
        this.bike.setResizeXY(spriteData[9], spriteData[10]);
        // this.bike.setShift(spriteData[11]);
        this.bike.setShift(2*dif + getRand(2));

        this.bike1 = new SpriteRectangle(getContext(), 0, 204, spriteSheetBike.getSprite());
        this.bike1.setResizeXY(spriteData[9], spriteData[10]);
        // this.bike1.setShift(spriteData[11]);
        this.bike1.setShift(2*dif + getRand(2));

        SpriteSheet spriteSheetbig_log1 = new SpriteSheet(context);
        spriteSheetbig_log1.setBitmap("big_log");
        this.big_log1 = new SpriteRectangle(getContext(), 0, 670, spriteSheetbig_log1.getSprite()); // dif = 160
        this.big_log1.setResizeXY(3*spriteData[9], spriteData[10]+10);
        //this.big_log1.setShift(spriteData[11]);
        this.big_log1.setShift(2*dif);

        // this.player.setShift(2*dif);

        SpriteSheet spriteSheetsmall_log1 = new SpriteSheet(context);
        spriteSheetsmall_log1.setBitmap("small_log");
        this.small_log1 = new SpriteRectangle(getContext(), 0, 510, spriteSheetsmall_log1.getSprite());
        this.small_log1.setResizeXY(2*spriteData[9], spriteData[10]+10);
        //this.big_log1.setShift(spriteData[11]);
        this.small_log1.setShift(2*dif);

        SpriteSheet spriteSheetbig_log2 = new SpriteSheet(context);
        spriteSheetbig_log2.setBitmap("big_log");
        this.big_log2 = new SpriteRectangle(getContext(), 0, 1470, spriteSheetbig_log2.getSprite()); // dif = 160
        this.big_log2.setResizeXY(3*spriteData[9], spriteData[10]+10);
        //this.big_log1.setShift(spriteData[11]);
        this.big_log2.setShift(2*dif);

        SpriteSheet spriteSheetsmall_log2 = new SpriteSheet(context);
        spriteSheetsmall_log2.setBitmap("small_log");
        this.small_log2 = new SpriteRectangle(getContext(), 0, 1630, spriteSheetsmall_log2.getSprite());
        this.small_log2.setResizeXY(2*spriteData[9], spriteData[10]+10);
        //this.big_log1.setShift(spriteData[11]);
        this.small_log2.setShift(2*dif);

        SpriteSheet spriteSheetbig_log3 = new SpriteSheet(context);
        spriteSheetbig_log3.setBitmap("big_log");
        this.big_log3 = new SpriteRectangle(getContext(), 0, 1790, spriteSheetbig_log3.getSprite()); // dif = 160
        this.big_log3.setResizeXY(3*spriteData[9], spriteData[10]+10);
        //this.big_log1.setShift(spriteData[11]);
        this.big_log3.setShift(2*dif);

        SpriteSheet spriteSheetsmall_log3 = new SpriteSheet(context);
        spriteSheetsmall_log3.setBitmap("small_log");
        this.small_log3 = new SpriteRectangle(getContext(), 0, 1950, spriteSheetsmall_log3.getSprite());
        this.small_log3.setResizeXY(2*spriteData[9], spriteData[10]+10);
        //this.big_log1.setShift(spriteData[11]);
        this.small_log3.setShift(2*dif);
    }

    private boolean checkCollision(SpriteRectangle sr) {
        if (player.getLeft() <= sr.getLeft() && player.getRight() >= sr.getLeft()) {
            return true;
        } else if (player.getRight() >= sr.getRight() && player.getLeft() <= sr.getRight()) {
            return true;
        } else if (player.getRight() <= sr.getRight() && player.getLeft() >= sr.getLeft()) {
            return true;
        }
        return false;
    }

    private void playerReset() {
        player.setYLevel(0);
        player.setLeft(670);
        player.setRight(812);
        player.setTop(2770);
        pointIndex = 0;
        points = 0;
    }

    private void obstacleCollision(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.yellow));
        paint.setTextSize(80);
        if (player.getYLevel() == 1) {
            if (checkCollision(truck)) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 2) {
            if (checkCollision(car)) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 3) {
            if (checkCollision(bike)) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        }
        /*
        else if (player.getYLevel() == 5) {
            if (player.getLeft() >= 0 && player.getLeft() < 510
                || player.getRight() >= 1000 && player.getRight() <= 1440) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }

        } else if (player.getYLevel() == 6 || player.getYLevel() == 7 || player.getYLevel() == 8) {
            if (player.getLeft() >= 0 && player.getLeft() < 450
                || player.getRight() > 790 && player.getRight() < 975 || player.getRight() > 1320) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } */
        else if (player.getYLevel() == 10) {
            if (checkCollision(truck1)) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 11) {
            if (checkCollision(car1)) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        }
        /*else if (player.getYLevel() == 13 || player.getYLevel() == 14) {
            if (player.getLeft() >= 0 && player.getLeft() < 450
                || player.getRight() > 790 && player.getRight() < 975 || player.getRight() > 1320) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } */
        else if (player.getYLevel() == 16) {
            if (checkCollision(bike1)) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        }
        else if (player.getYLevel() == 5) {
            if (!checkCollision(small_log3)) {
                canvas.drawText(
                        "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                        100, 120, paint);
                playerReset();
            }
            else{
                player.moveRight(2*difficulty);
            }
        }
        else if (player.getYLevel() == 6) {
            if (!checkCollision(big_log3)) {
                canvas.drawText(
                        "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                        100, 120, paint);
                playerReset();
            }
            else{
                player.moveRight(2*difficulty);
            }
        }
        else if (player.getYLevel() == 7) {
            if (!checkCollision(small_log2)) {
                canvas.drawText(
                        "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                        100, 120, paint);
                playerReset();
            }
            else{
                player.moveRight(2*difficulty);
            }
        }
        else if (player.getYLevel() == 8) {
            if (!checkCollision(big_log2)) {
                canvas.drawText(
                        "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                        100, 120, paint);
                playerReset();
            }
            else{
                player.moveRight(2*difficulty);
            }
        }
        else if (player.getYLevel() == 13) {
            if (!checkCollision(big_log1)) {
                canvas.drawText(
                        "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                        100, 120, paint);
                playerReset();
            }
            else{
               player.moveRight(2*difficulty);
            }
        }
        else if (player.getYLevel() == 14) {
            if (!checkCollision(small_log1)) {
                canvas.drawText(
                        "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                        100, 120, paint);
                playerReset();
            }
            else{
                player.moveRight(2*difficulty);
            }
        }
    }


    public void setSprite(String character) {
        spriteSheetPlayer.setBitmap(character);
        this.player = new SpriteRectangle(getContext(), 670, 2770,
            spriteSheetPlayer.getSprite()); // TODO --> This line sets player original position
        this.player.setResizeXY(spriteData[0], spriteData[1]);
        this.player.setShift(spriteData[2]);
    }

    public static void addPoints(int add){
        points += add;
    }
    public static void addWinBonus(){
        addPoints(200);
    }
    public static int getPoints() {
        return points;
    }

    public static int getMaxPoints() {
        return maxPoints;
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
        //gameLoop.startLoop();
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
        obstacleMovement(canvas);

        player.drawBackground(canvas);

        big_log1.draw1(canvas);
        small_log1.draw1(canvas);
        big_log2.draw1(canvas);
        small_log2.draw1(canvas);
        big_log3.draw1(canvas);
        small_log3.draw1(canvas);

        //dummy.draw(canvas);
        player.draw1(canvas);

        truck.draw1(canvas);
        truck1.draw1(canvas);
        car.draw1(canvas);
        car1.draw1(canvas);
        bike.draw1(canvas);
        bike1.draw1(canvas);

        obstacleCollision(canvas);
        calculatePoints(canvas); //TODO Clean
        invalidate();
    }

    private void obstacleMovement(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.yellow));
        paint.setTextSize(80);
        if (truck.getLeft() > 0) {
            truck.moveLeft();
            truck1.moveLeft();
        } else {
            truck.setLeft(1014);
            truck1.setLeft(1014);
            truck.setRight(1014 + truck.getSprite().getWidth());
            truck1.setRight(1014 + truck1.getSprite().getWidth());
        }
        if (car.getLeft() <= 1440) {
            car.moveRight();
            car1.moveRight();
        } else {
            car.setLeft(0);
            car1.setLeft(0);
            car.setRight(car.getSprite().getWidth());
            car1.setRight(car1.getSprite().getWidth());
        }
        if (bike.getLeft() <= 1440) {
            bike.moveRight();
            bike1.moveRight();
        } else {
            bike.setLeft(0);
            bike1.setLeft(0);
            bike.setRight(bike.getSprite().getWidth());
            bike1.setRight(bike1.getSprite().getWidth());
        }

        if (big_log1.getLeft() <= 1440) {
            big_log1.moveRight();
           // player.moveRight();
        } else {
            big_log1.setLeft(0);
            big_log1.setRight(big_log1.getSprite().getWidth());
        }
        if (small_log1.getLeft() <= 1440) {
            small_log1.moveRight();
        } else {
            small_log1.setLeft(0);
            small_log1.setRight(small_log1.getSprite().getWidth());
        }
        if (big_log2.getLeft() <= 1440) {
            big_log2.moveRight();
        } else {
            big_log2.setLeft(0);
            big_log2.setRight(big_log2.getSprite().getWidth());
        }
        if (small_log2.getLeft() <= 1440) {
            small_log2.moveRight();
        } else {
            small_log2.setLeft(0);
            small_log2.setRight(small_log2.getSprite().getWidth());
        }

        if (big_log3.getLeft() <= 1440) {
            big_log3.moveRight();
        } else {
            big_log3.setLeft(0);
            big_log3.setRight(big_log3.getSprite().getWidth());
        }
        if (small_log3.getLeft() <= 1440) {
            small_log3.moveRight();
        } else {
            small_log3.setLeft(0);
            small_log3.setRight(small_log2.getSprite().getWidth());
        }
        if (!(player.getLeft() <= 1440)) {
            if (checkCollision(car)) {
                canvas.drawText(
                        "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                        100, 120, paint);
                playerReset();
            }
        }
    }

    public void calculatePoints(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.yellow));
        paint.setTextSize(80);
        if (player.getTop() < minPos) {
            minPos = player.getTop();
            addPoints(pointsArray[++pointIndex]);
            maxPoints = Math.max(maxPoints, points);
        }
        // TODO make player lose life
        canvas.drawText("Points: " + points + "  Lives: " + lives + " Difficulty: " + difficulty,
            100, 120, paint);
    }


    public SpriteRectangle getPlayer() {
        return player;
    }

    public int getPointIndex() {
        return pointIndex;
    }

    public int getLives() {
        return lives;
    }

    public SpriteSheet getSpriteSheetPlayer() {
        return spriteSheetPlayer;
    }
}