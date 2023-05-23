package com.example.calculmind;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        String limit = "5";

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
            topScores.add(name + "\n" + score + " points");
        }
        cursor.close();

        // boucle for de 0 a 2
        for (int i = 0; i < 2; i++) {
            StringBuilder sb = new StringBuilder();
            try{
                sb.append(topScores.get(i));
            } catch (IndexOutOfBoundsException e) {
                sb.append(" ");
            }
            sb.append("\n");
            String topScoresText = sb.toString();
            switch (i){
                case 0:
                    TextView textView = findViewById(R.id.text_view_winner);
                    textView.setText(topScoresText);
                case 1:
                    TextView textView1 = findViewById(R.id.text_view_second);
                    textView1.setText(topScoresText);
                case 2:
                    TextView textView2 = findViewById(R.id.text_view_troisieme);
                    textView2.setText(topScoresText);
            }


        }


        button.setOnClickListener(view -> {
            finish();
        });
    }
}