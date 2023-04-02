package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.example.myapplication.graphics.SpriteRectangle;
import com.example.myapplication.graphics.SpriteSheet;

//TODO Refactor variable names and add variable speeds based on lane
// (higher lane has higher speed, lower lanes have lower speed)
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private SpriteRectangle player;
    // private Player player;
    private SpriteRectangle truck;
    private SpriteRectangle truck1;
    private SpriteRectangle car;
    private SpriteRectangle car1;
    private SpriteRectangle bike;
    private SpriteRectangle bike1;

    private final SpriteSheet spriteSheetPlayer;

    private int difficulty;
    public static int lives;
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

    public Game(Context context) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //this.gameLoop = new GameLoop(this, surfaceHolder);
        spriteSheetPlayer = new SpriteSheet(context);
        constructObstacles(context);
        this.setFocusable(true);

    }

    private void constructObstacles(Context context) {
        SpriteSheet spriteSheetTruck = new SpriteSheet(context);
        spriteSheetTruck.setBitmap("truck");
        this.truck = new SpriteRectangle(getContext(), 0, 2608, spriteSheetTruck.getSprite());
        this.truck.setResizeXY(spriteData[3], spriteData[4]);
        this.truck.setShift(spriteData[5]);


        this.truck1 = new SpriteRectangle(getContext(), 0, 1176, spriteSheetTruck.getSprite());
        this.truck1.setResizeXY(spriteData[3], spriteData[4]);
        this.truck1.setShift(spriteData[5]);


        SpriteSheet spriteSheetCar = new SpriteSheet(context);
        spriteSheetCar.setBitmap("blueCar");
        this.car = new SpriteRectangle(getContext(), 1014, 2446, spriteSheetCar.getSprite());
        this.car.setResizeXY(spriteData[6], spriteData[7]);
        this.car.setShift(spriteData[8]);


        this.car1 = new SpriteRectangle(getContext(), 1014, 1014, spriteSheetCar.getSprite());
        this.car1.setResizeXY(spriteData[6], spriteData[7]);
        this.car1.setShift(spriteData[8]);


        SpriteSheet spriteSheetBike = new SpriteSheet(context);
        spriteSheetBike.setBitmap("motorcycle");
        this.bike = new SpriteRectangle(getContext(), 0, 2284, spriteSheetBike.getSprite());
        this.bike.setResizeXY(spriteData[9], spriteData[10]);
        this.bike.setShift(spriteData[11]);

        this.bike1 = new SpriteRectangle(getContext(), 0, 204, spriteSheetBike.getSprite());
        this.bike1.setResizeXY(spriteData[9], spriteData[10]);
        this.bike1.setShift(spriteData[11]);
    }

    private boolean checkCollision(SpriteRectangle sr) {
        if (player.getLeft() <= sr.getLeft() && player.getRight() >= sr.getLeft()) {
            /*
            System.out.println(player.getLeft());
            System.out.println(player.getRight());
            System.out.println(sr.getLeft());
            System.out.println(sr.getRight());

            System.out.println("flag 1");*/
            return true;
        } else if (player.getRight() >= sr.getRight() && player.getLeft() <= sr.getRight()) {
            // System.out.println("flag 2");
            return true;
        } else if (player.getRight() <= sr.getRight() && player.getLeft() >= sr.getLeft()) {
            //System.out.println("flag 3");
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
                System.out.println(
                    "TRUCK LEFT: " + truck.getLeft() + " TRUCK RIGHT: " + truck.getRight());
                System.out.println(
                    "PLAYER LEFT: " + player.getLeft() + " PLAYER RIGHT: " + player.getRight() +
                        "Width " + (player.getLeft() - player.getRight()));
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 2) {
            if (checkCollision(car)) {
                System.out.println("CAR");
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 3) {
            if (checkCollision(bike)) {
                System.out.println("BIKE");
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 5) {
            if (player.getLeft() >= 0 && player.getLeft() < 510 ||
                player.getRight() >= 1000 && player.getRight() <= 1440) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }

        } else if (player.getYLevel() == 6 || player.getYLevel() == 7) {
            if (player.getLeft() >= 0 && player.getLeft() < 450 ||
                player.getRight() > 790 && player.getRight() < 975 || player.getRight() > 1320) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 8) {
            //TODO fix water tiles
        } else if (player.getYLevel() == 10) {
            if (checkCollision(truck1)) {
                System.out.println("TRUCK1");
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 11) {
            if (checkCollision(car1)) {
                System.out.println("CAR1");
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 13 || player.getYLevel() == 14) {
            if (player.getLeft() >= 0 && player.getLeft() < 450 ||
                player.getRight() > 790 && player.getRight() < 975 || player.getRight() > 1320) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        } else if (player.getYLevel() == 16) {
            if (checkCollision(bike1)) {
                canvas.drawText(
                    "Points: " + points + "  Lives: " + lives-- + " Difficulty: " + difficulty,
                    100, 120, paint);
                playerReset();
            }
        }

    }

    public void setSprite(String character) {
        spriteSheetPlayer.setBitmap(character);
        this.player = new SpriteRectangle(getContext(), 670, 2770,
            spriteSheetPlayer.getSprite()); // TODO --> This line sets player original position
        this.player.setResizeXY(spriteData[0], spriteData[1]);
        this.player.setShift(spriteData[2]);
        System.out.println(spriteSheetPlayer.getWidth());
        System.out.println(player.getLeft() + " " + player.getRight());
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
        obstacleMovement();
        player.draw(canvas);
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

    private void obstacleMovement() {
        if (truck.getLeft() > 0) {
            //System.out.println("Left: "+ truck.getLeft()+" Right: "+truck.getRight());
            truck.moveLeft();
            truck1.moveLeft();
        } else {
            //System.out.println(truck.getLeft());
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

    }

    public void calculatePoints(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.yellow));
        paint.setTextSize(80);
        if (player.getTop() < minPos) {
            minPos = player.getTop();
            points += pointsArray[++pointIndex];
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

    public SpriteSheet getSpriteSheetPlayer() {
        return spriteSheetPlayer;
    }
}