package com.example.calculmind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    private Button button_retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        button_retour = findViewById(R.id.button_retour_accueil_about);
        button_retour.setOnClickListener(view -> {
            Intent intent = new Intent(AboutActivity.this,MainActivity.class);
            startActivity(intent);
        });
    }
}