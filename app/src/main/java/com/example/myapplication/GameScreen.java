package com.example.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity {
    TextView CharDisplay;
    TextView difficulty;
    TextView lives;
    TextView highScore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Player user = (Player) getIntent().getParcelableExtra("user");
        ImageView picture = findViewById(R.id.imageView4);
        if (user.getSprite().equals("Monopoly Man")) {
            picture.setImageResource(R.drawable.monopoly_man);
        } else if (user.getSprite().equals("Dawg")) {
            picture.setImageResource(R.drawable.monopoly_dog);
        } else if(user.getSprite().equals("Shoe")){
            picture.setImageResource(R.drawable.monopoly_shoe);
        }
        CharDisplay = findViewById(R.id.textView7);
        CharDisplay.setText("Hey "+user.getName());
        difficulty = findViewById(R.id.textView10);
        switch(user.getDifficulty()) {
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
        lives = findViewById(R.id.textView12);
        lives.setText(""+user.getLives());
        highScore = findViewById(R.id.textView14);
        highScore.setText("High Score "+user.getPoints());




    }
}
