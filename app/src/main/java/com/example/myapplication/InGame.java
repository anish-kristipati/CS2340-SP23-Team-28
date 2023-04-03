package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


import com.example.myapplication.graphics.SpriteRectangle;

public class InGame extends Activity implements View.OnTouchListener {
    private GestureDetector gestureDetector;
    private Game game;
    private SpriteRectangle player;
    private Player user;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            user = getIntent().getParcelableExtra("user");
            user.getSprite();
        } catch (NullPointerException e) {
            user = new Player();
            user.setSprite("dawg");
            user.setDifficulty(1);
        }
        //Set content view to game
        game = new Game(this);
        game.setSprite(user.getSprite());
        game.setCharacterData(user.getLives(), user.getDifficulty(), user.getPoints());
        game.setBackgroundColor(Color.WHITE);
        player = game.getPlayer();
        this.setContentView(game);


        gestureDetector = new GestureDetector(this, new OnSwipeListener() {

            @Override
            public boolean onSwipe(Direction direction) {
                switch (direction) {
                case up:
                    endGame();
                    if (player.getTop() > 142) {

                        player.moveUp();
                    }
                    break;
                case down:
                    endGame();
                    if (player.getTop() < 2770) {
                        player.moveDown();
                    }
                    break;
                case left:
                    endGame();
                    if (player.getLeft() > 0) {
                        player.moveLeft();
                    }
                    break;
                case right:
                    endGame();
                    if (player.getLeft() < 1280) {
                        player.moveRight();
                    }
                    break;
                default:
                    System.out.println("Whoops");
                    break;
                }
                return true;
            }


        });
        game.setOnTouchListener(this);
    }


    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    public void endGame() {
        if (game.getLives() <= 0 || game.getPointIndex() >= 15) {
            Intent switchStatement = new Intent(InGame.this, GameOverScreen.class);
            startActivity(switchStatement);

        }
    }

    public Game getGame() {
        return game;
    }

    public Player getUser() {
        return user;
    }

    public SpriteRectangle getPlayer() {
        return player;
    }

}

