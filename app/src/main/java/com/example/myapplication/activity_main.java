package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_main extends AppCompatActivity {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.startGame);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent switchStatement = new Intent(activity_main.this,Select.class);
                startActivity(switchStatement);

            }
        });
    }

}
