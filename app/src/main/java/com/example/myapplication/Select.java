package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        inputText = (EditText) findViewById(R.id.editTextTextPersonName2);
        header = (TextView)findViewById(R.id.textView);
        charData = (TextView)findViewById(R.id.textView4);
        //ToDo - Check NonNull / Whitespace etc
        inputText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    user.setName(String.valueOf(inputText.getText()));
                    header.setText("Welcome " + user.getName() + "!");

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
                charData.setText("Difficulty "+ user.getDifficulty());
            }
        });

    }

    public void selectCharacter1 (View view){
        user.setSprite("Monopoly Man");
        charData.setText("Character: Monopoly Man \n Difficulty:");

    }
    public void selectCharacter2 (View view){
        user.setSprite("Shoe");
        charData.setText("Character: Shoe \n Difficulty:");

    }
    public void selectCharacter3 (View view){
        user.setSprite("Dawg");
        charData.setText("Character: Dawg \n Difficulty:");
    }

}