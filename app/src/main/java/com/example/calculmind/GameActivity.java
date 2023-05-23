package com.example.calculmind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculmind.database.ScoreDatabaseHelper;

public class GameActivity extends AppCompatActivity {

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonEnter;
    private Button buttonDelete;
    private Button buttonDot;

    private Button buttonAbandon;
    private MenuItem toolbarscrore;
    private MenuItem toolbarcoeur1;

    private  MenuItem toolbarcoeur2;

    private  MenuItem toolbarcoeur3;

    private TextView textViewInput;
    private TextView textViewCalcul;

    private String calculShow;
    private double calculResult;
    private String enterNumber= null;
    private Integer healthBar=3;
    private Integer score=0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        MenuItem menuItem = menu.findItem(R.id.toolbar_abandon);
        menuItem.setOnMenuItemClickListener(view -> abandon());
        toolbarscrore = menu.findItem(R.id.toolbar_score);
        toolbarcoeur1 = menu.findItem(R.id.toolbar_coeur1);
        toolbarcoeur2 = menu.findItem(R.id.toolbar_coeur2);
        toolbarcoeur3 = menu.findItem(R.id.toolbar_coeur3);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean abandon() {
        while(healthBar != 0){
            healthBar--;
            checkHealthBar();
        }
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textViewInput = findViewById(R.id.textViewInput);
        textViewCalcul = findViewById(R.id.textViewCalcul);
        healthBar = 3;
        score = 0;
        enterNumber = null;
        generateNumber();
        buttonDot = findViewById(R.id.button_dot);
        buttonDot.setOnClickListener(view -> addNumberVirgule());

        button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(view -> addNumber(0));

        button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(view -> addNumber(1));

        button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(view -> addNumber(2));

        button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(view -> addNumber(3));

        button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(view -> addNumber(4));

        button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(view -> addNumber(5));

        button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(view -> addNumber(6));

        button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(view -> addNumber(7));

        button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(view -> addNumber(8));

        button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(view -> addNumber(9));

        buttonEnter = findViewById(R.id.button_entry);
        buttonEnter.setOnClickListener(view -> checkResult());

        buttonDelete = findViewById(R.id.button_del);
        buttonDelete.setOnClickListener(view -> deleteNumber());

    }

    private void addNumber(int number) {
        if (enterNumber == null) {
            enterNumber = String.valueOf(number);
        } else {
            enterNumber = enterNumber + number;
        }
        textViewCalcul.setText(enterNumber);
    }

    private void addNumberVirgule() {
        if (enterNumber == null) {
            enterNumber = "0.";
        } else {
            enterNumber = enterNumber + ".";
        }
        textViewCalcul.setText(enterNumber);
    }

    private void deleteNumber() {
        if (enterNumber != null) {
            if (enterNumber.length() == 1) {
                enterNumber = null;
                textViewCalcul.setText("...");
            } else {
                enterNumber = enterNumber.substring(0, enterNumber.length() - 1);
                textViewCalcul.setText(enterNumber);
            }

        }
    }


    private void generateNumber() {
        String[] operator = {"+", "-", "*", "/"};
        int number1 = (int) (Math.random() * 15)+1;
        int number2 = (int) (Math.random() * 15)+1;
        if(number1 < number2){
            int temp = number1;
            number1 = number2;
            number2 = temp;
        }
        int operatorNumber = (int) (Math.random() * 4);
        calculShow = number1 + operator[operatorNumber] + number2;
        calculResult = calculate(number1, number2, operator[operatorNumber]);
        textViewInput.setText(calculShow);

    }

    private double calculate(int number1, int number2, String s) {
        switch (s) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                //arondir le resultat a au dixieme pres
                return Math.floor(( (double)number1 / (double)number2) * 10) / 10.0;
            default:
                calculShow = "0";
                return 0;
        }
    }

    private void checkResult() {
        if (enterNumber != null && calculResult == Double.parseDouble(enterNumber)) {
            score++;
            toolbarscrore.setTitle(score.toString());
            generateNumber();
        } else {
            healthBar--;
            checkHealthBar();
        }
        enterNumber = null;
        textViewCalcul.setText("...");
    }

    private void checkHealthBar() {

        if (healthBar <= 0) {
            Dialog scoreDialog = new Dialog(this);
            scoreDialog.setContentView(R.layout.popup_score);
            TextView scoreTextView = scoreDialog.findViewById(R.id.textView_score);
            EditText playerName = scoreDialog.findViewById(R.id.TextPersonName);
            Button valideButton = scoreDialog.findViewById(R.id.buttonScore_valider);
            Button cancelButton = scoreDialog.findViewById(R.id.buttonScore_annuler);
            scoreTextView.setText(getString(R.string.text_popup_message) + " " + score + " " + getString(R.string.text_popup_message2));
            //todo trad

            ScoreDatabaseHelper dbHelper = new ScoreDatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            valideButton.setOnClickListener(view -> {
                ContentValues values = new ContentValues();
                //if player name is empty, set default name
                if(playerName.getText().toString().isEmpty()){
                    values.put("name", "John Doe");
                }
                values.put("name", playerName.getText().toString());
                values.put("score", score);
                db.insert("scores", null, values);

                scoreDialog.dismiss();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });

            cancelButton.setOnClickListener(view -> {
                scoreDialog.dismiss();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });

            scoreDialog.show();
        }

        switch (healthBar){
            case 2:
                toolbarcoeur1.setIcon(R.color.fond_app);
                generateNumber();
                break;
            case 1:
                toolbarcoeur2.setIcon(R.color.fond_app);
                generateNumber();
                break;
            case 0:
                toolbarcoeur3.setIcon(R.color.fond_app);
                //lancer page de fin
                break;
        }
    }



}