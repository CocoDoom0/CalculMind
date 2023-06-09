package com.example.calculmind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;

    private Button buttonHighScore;

    private Button buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.button);
        buttonHighScore = findViewById(R.id.button2);
        buttonAbout = findViewById(R.id.button_about);

        buttonStart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,GameActivity.class);
            startActivity(intent);
        });
        buttonHighScore.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,HighScoreActivity.class);
            startActivity(intent);
        });

        buttonAbout.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
        });
    }
}