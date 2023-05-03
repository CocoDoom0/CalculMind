package com.example.calculmind;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculmind.database.ScoreDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Button button = findViewById(R.id.btnAcceuil);
        ScoreDatabaseHelper dbHelper = new ScoreDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "id",
                "name",
                "score"
        };

        String sortOrder = "score DESC";
        String limit = "10";

        Cursor cursor = db.query(
                "scores",            // Nom de la table
                projection,          // Colonnes à retourner
                null,                // Colonne de sélection (null pour toutes les lignes)
                null,                // Arguments de sélection (null signifie pas d'argument)
                null,                // Groupes de lignes (null signifie pas de groupement)
                null,                // Filtre des groupes de lignes (null signifie pas de filtre)
                sortOrder,           // Trier les lignes par ordre décroissant de score
                limit                // Nombre maximal de lignes à retourner
        );

        List<String> topScores = new ArrayList<>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
            topScores.add(name + " : " + score);
        }
        cursor.close();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(topScores.size(), 10); i++) {
            sb.append(topScores.get(i));
            sb.append("\n");
        }
        String topScoresText = sb.toString();
        TextView textView = findViewById(R.id.textViewInput);
        textView.setText(topScoresText);

        button.setOnClickListener(view -> {
            finish();
        });
    }
}