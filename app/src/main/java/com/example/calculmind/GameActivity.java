package com.example.calculmind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

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

    private String calculShow;
    private double calculResult;
    private String enterNumber= null;
    private Integer healthBar=0;
    private Integer score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        healthBar = 3;
        score = 0;
        enterNumber = null;
        generateNumber();
        setContentView(R.layout.activity_game);

        buttonVirgule = findViewById(R.id.buttonVirgule);
        buttonVirgule.setOnClickListener(view -> addNumberVirgule());

        button0 = findViewById(R.id.button0);
        button0.setOnClickListener(view -> addNumber(0));

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(view -> addNumber(1));

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> addNumber(2));

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> addNumber(3));

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> addNumber(4));

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(view -> addNumber(5));

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(view -> addNumber(6));

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(view -> addNumber(7));

        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(view -> addNumber(8));

        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(view -> addNumber(9));

        buttonEnter = findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(view -> );

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(view -> deleteNumber());;

    }

    private void addNumber(int number) {
        if (enterNumber == null) {
            enterNumber = String.valueOf(number);
        } else {
            enterNumber = enterNumber + number;
        }
    }

    private void addNumberVirgule() {
        if (enterNumber == null) {
            enterNumber = "0.";
        } else {
            enterNumber = enterNumber + ".";
        }
    }

    private void deleteNumber() {
        if (enterNumber != null) {
            if (enterNumber.length() == 1) {
                enterNumber = null;
            } else {
                enterNumber = enterNumber.substring(0, enterNumber.length() - 1);
            }
        }
    }

    private void generateNumber() {
        String[] operator = {"+", "-", "*", "/"};
        int number1 = (int) (Math.random() * 100);
        int number2 = (int) (Math.random() * 100);
        int operatorNumber = (int) (Math.random() * 3);
        calculShow = number1 + operator[operatorNumber] + number2;
        calculResult = calculate(number1, number2, operator[operatorNumber]);
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
                return Math.round(( (double)number1 / (double)number2) * 10) / 10.0;
            default:
                calculShow = "0";
                return 0;
        }
    }

    private void checkResult() {
        if (enterNumber != null && calculResult == Double.parseDouble(enterNumber)) {
            score++;
            generateNumber();
        } else {
            healthBar--;
            checkHealthBar();
        }
    }

    private void checkHealthBar() {
        if (healthBar == 0) {
            //todo
        }
    }



}