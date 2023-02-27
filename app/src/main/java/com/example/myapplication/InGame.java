package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.graphics.Player;

public class InGame extends Activity implements View.OnTouchListener{
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Set content view to game

        //SurfaceView game = (Game)(findViewById(R.id.game1));
        SurfaceView game = new Game(this);
        Game game1 =  (Game)game;
        game.setBackgroundColor(Color.WHITE);
        Player player = game1.getPlayer();
        this.setContentView(game1);
        gestureDetector = new GestureDetector(this,new OnSwipeListener(player){

            @Override
            public boolean onSwipe(Direction direction) {
                switch(direction) {
                case up:
                    if(player.getTop() > 142) {
                        player.moveUp();
                    }
                    break;
                case down:
                    if(player.getTop() < 2770) {
                        player.moveDown();
                    }
                    break;
                case left:
                    if(player.getLeft() > 0) {
                        player.moveLeft();
                    }
                    break;
                case right:
                    if(player.getLeft() < 1280) {
                        player.moveRight();
                    }
                }
                return true;
            }


        });
        game.setOnTouchListener(this);
    }


    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

}

