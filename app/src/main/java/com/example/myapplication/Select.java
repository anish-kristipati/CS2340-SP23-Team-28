package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;

public class Select extends AppCompatActivity {
    EditText inputText;
    TextView header;
    TextView charData;
    Player user = new Player();
    Button b2;
    String output = "";
    TextView warning;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        inputText = (EditText) findViewById(R.id.editTextTextPersonName2);
        header = (TextView) findViewById(R.id.textView);
        charData = (TextView) findViewById(R.id.textView4);
        b2 = findViewById(R.id.buttonNext);
        b2.setEnabled(false);
        warning = findViewById(R.id.textView5);
        warning.setVisibility(View.INVISIBLE);
        inputText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (String.valueOf(inputText.getText()) == null || String.valueOf(inputText.getText()).equals("") || String.valueOf(inputText.getText()).trim().length() == 0) {
                        warning.setVisibility(View.VISIBLE);
                    } else {
                        user.setName(String.valueOf(inputText.getText()));
                        warning.setVisibility(View.INVISIBLE);
                        header.setText("Welcome " + user.getName() + "!");
                    }
                    if (user.getName() != null && user.getDifficulty() != 0 && user.getSprite() != null) {
                        b2.setEnabled(true);
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent switchStatement = new Intent(Select.this, GameScreen.class);
                                switchStatement.putExtra("user", user);
                                startActivity(switchStatement);

                            }
                        });
                    }
                }
                return false;
            }
        });
        Slider slider = findViewById(R.id.slider);
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                Integer slide_value = Math.round(value);
                user.setDifficulty(slide_value);
                charData.setText(output + "\nDifficulty: " + user.getDifficulty());
                if (user.getName() != null && user.getDifficulty() != 0 && user.getSprite() != null) {
                    b2.setEnabled(true);
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent switchStatement = new Intent(Select.this, GameScreen.class);
                            switchStatement.putExtra("user", user);
                            startActivity(switchStatement);

                        }
                    });
                }
            }
        });
    }


    public void selectCharacter1(View view) {
        user.setSprite("Monopoly Man");
        charData.setText("Character: Monopoly Man \n Difficulty:");
        output = "Character: Monopoly Man";
        if (user.getName() != null && user.getDifficulty() != 0 && user.getSprite() != null) {
            b2.setEnabled(true);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent switchStatement = new Intent(Select.this, GameScreen.class);
                    switchStatement.putExtra("user", user);
                    startActivity(switchStatement);

                }
            });
        }
    }

    public void selectCharacter2(View view) {
        user.setSprite("Shoe");
        charData.setText("Character: Shoe \n Difficulty:");
        output = "Character: Shoe";
        if (user.getName() != null && user.getDifficulty() != 0 && user.getSprite() != null) {
            b2.setEnabled(true);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent switchStatement = new Intent(Select.this, GameScreen.class);
                    switchStatement.putExtra("user", user);
                    startActivity(switchStatement);

                }
            });
        }
    }

    public void selectCharacter3(View view) {
        user.setSprite("Dawg");
        charData.setText("Character: Dawg \n Difficulty:");
        output = "Character: Dawg";
        if (user.getName() != null && user.getDifficulty() != 0 && user.getSprite() != null) {
            b2.setEnabled(true);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent switchStatement = new Intent(Select.this, GameScreen.class);
                    switchStatement.putExtra("user", user);
                    startActivity(switchStatement);

                }
            });
        }
    }

}

