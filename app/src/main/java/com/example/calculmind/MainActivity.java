package com.example.calculmind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;

    private Button buttonHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.buttonStart);
        buttonHighScore = findViewById(R.id.buttonHighScore);
        buttonStart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,GameActivity.class);
            startActivity(intent);
        });
        buttonHighScore.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,HighScoreActivity.class);
            startActivity(intent);
        });
    }
}