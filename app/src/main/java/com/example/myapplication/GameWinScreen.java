package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameWinScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_win);
        TextView points = (TextView) findViewById(R.id.points1);
        points.setText("Points: " + Game.getMaxPoints());
        Button exit = findViewById(R.id.exitgame1);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameWinScreen.this.finishAffinity();
                System.exit(0);
            }
        });
        Button restart = findViewById(R.id.restart1);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchStatement = new Intent(GameWinScreen.this, ActivityMain.class);
                startActivity(switchStatement);
            }
        });
    }
}
