package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Player user = getIntent().getParcelableExtra("user");
        ImageView picture = findViewById(R.id.imageView4);
        if (user.getSprite().equals("Monopoly Man")) {
            picture.setImageResource(R.drawable.monopoly_man);
        } else if (user.getSprite().equals("Dawg")) {
            picture.setImageResource(R.drawable.monopoly_dog);
        } else if (user.getSprite().equals("Shoe")) {
            picture.setImageResource(R.drawable.monopoly_shoe);
        }
        TextView charDisplay = findViewById(R.id.textView7);
        charDisplay.setText("Hey " + user.getName());
        TextView difficulty = findViewById(R.id.textView10);
        switch (user.getDifficulty()) {
        case 1:
            difficulty.setText("Easy");
            break;
        case 2:
            difficulty.setText("Medium");
            break;
        case 3:
            difficulty.setText("Hard");
            break;
        default:
            difficulty.setText("Easy");
        }
        TextView lives = findViewById(R.id.textView12);
        lives.setText("" + user.getLives());
        TextView highScore = findViewById(R.id.textView14);
        highScore.setText("High Score " + user.getPoints());

        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchStatement = new Intent(GameScreen.this, InGame.class);
                switchStatement.putExtra("user", user);
                startActivity(switchStatement);

            }
        });

    }
}
