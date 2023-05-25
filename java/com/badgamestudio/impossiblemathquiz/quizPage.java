package com.badgamestudio.impossiblemathquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class quizPage extends AppCompatActivity {

    TextView scoreText;
    TextView timer;
    TextView number1;
    TextView number2;
    TextView mathOperation;
    TextView userAnswer;

    AdView quizPage_adView;

    int score;
    int number_1;
    int number_2;
    int randomOperation;
    int right_answer;

    String answerText;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SCORE_TEXT = "score_text";

    private String load_score;

    CountDownTimer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_page);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        quizPage_adView = findViewById(R.id.quizPage_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        quizPage_adView.loadAd(adRequest);

        scoreText = findViewById(R.id.scoreText);
        timer = findViewById(R.id.timer);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        mathOperation = findViewById(R.id.mathOperation);
        userAnswer = findViewById(R.id.userAnswer);

        score = Integer.parseInt(scoreText.getText().toString());
        number_1 = Integer.parseInt(number1.getText().toString());
        number_2 = Integer.parseInt(number2.getText().toString());

        loadScore();
        updateScore();

        if (score <= 990) {
            count = new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    saveScore();

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(new Intent(quizPage.this, losePage.class));
                    overridePendingTransition(0, 0);
                }
            }.start();
        } else if (score >= 991) {
            count = new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    count.cancel();
                    saveScore();

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(new Intent(quizPage.this, losePage.class));
                    overridePendingTransition(0, 0);
                }
            }.start();
        }

        if (score <= 50) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(30) + 1;
                number_2 = new Random().nextInt(30) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(30) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(10) + 1;
                number_2 = new Random().nextInt(10) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(10) + 1;
                number_1 = number_2 * (new Random().nextInt(5) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 51) && (score <= 200)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(50) + 1;
                number_2 = new Random().nextInt(50) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(50) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(15) + 1;
                number_2 = new Random().nextInt(15) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(15) + 1;
                number_1 = number_2 * (new Random().nextInt(10) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 201) && (score <= 300)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(100) + 1;
                number_2 = new Random().nextInt(100) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(100) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(20) + 1;
                number_2 = new Random().nextInt(20) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(20) + 1;
                number_1 = number_2 * (new Random().nextInt(10) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 301) && (score <= 400)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(200) + 1;
                number_2 = new Random().nextInt(200) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(200) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(30) + 1;
                number_2 = new Random().nextInt(30) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(30) + 1;
                number_1 = number_2 * (new Random().nextInt(15) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 401) && (score <= 500)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(300) + 1;
                number_2 = new Random().nextInt(300) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(300) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(40) + 1;
                number_2 = new Random().nextInt(40) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(40) + 1;
                number_1 = number_2 * (new Random().nextInt(15) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 501) && (score <= 600)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(400) + 1;
                number_2 = new Random().nextInt(400) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(400) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(50) + 1;
                number_2 = new Random().nextInt(50) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(50) + 1;
                number_1 = number_2 * (new Random().nextInt(15) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 601) && (score <= 700)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(500) + 1;
                number_2 = new Random().nextInt(500) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(500) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(60) + 1;
                number_2 = new Random().nextInt(60) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(60) + 1;
                number_1 = number_2 * (new Random().nextInt(15) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 701) && (score <= 800)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(600) + 1;
                number_2 = new Random().nextInt(600) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(600) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(70) + 1;
                number_2 = new Random().nextInt(70) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(70) + 1;
                number_1 = number_2 * (new Random().nextInt(20) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 801) && (score <= 900)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(700) + 1;
                number_2 = new Random().nextInt(700) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(700) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(80) + 1;
                number_2 = new Random().nextInt(80) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(80) + 1;
                number_1 = number_2 * (new Random().nextInt(20) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 901) && (score <= 990)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(800) + 1;
                number_2 = new Random().nextInt(800) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(800) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(80) + 1;
                number_2 = new Random().nextInt(80) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(80) + 1;
                number_1 = number_2 * (new Random().nextInt(25) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }

        if ((score >= 991) && (score <= 1000)) {
            randomOperation = new Random().nextInt(4) + 1;
            if (randomOperation == 1) {
                mathOperation.setText("+");
                number_1 = new Random().nextInt(999) + 1;
                number_2 = new Random().nextInt(999) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 + number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 2) {
                mathOperation.setText("-");
                number_1 = new Random().nextInt(999) + 1;
                number_2 = (new Random().nextInt(number_1));
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 - number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 3) {
                mathOperation.setText("x");
                number_1 = new Random().nextInt(99) + 1;
                number_2 = new Random().nextInt(99) + 1;
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 * number_2;
                answerText = String.valueOf(right_answer);
            }
            else if (randomOperation == 4) {
                mathOperation.setText(":");
                number_2 = new Random().nextInt(99) + 1;
                number_1 = number_2 * (new Random().nextInt(30) + 1);
                number1.setText("" + number_1);
                number2.setText("" + number_2);
                right_answer = number_1 / number_2;
                answerText = String.valueOf(right_answer);
            }
        }
    }

    public void submitButton (View view){
        if ((userAnswer.getText().toString()).equals(answerText)) {
            score++;
            scoreText.setText("" + score);
            count.cancel();
            saveScore();

            if (scoreText.getText().toString().equals("1000")){
                count.cancel();
                finish();
                overridePendingTransition(0, 0);
                startActivity(new Intent(quizPage.this, winPage.class));
                overridePendingTransition(0, 0);
            }
            else if (!scoreText.getText().toString().equals("1000")) {
                count.cancel();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        }
        else if (!(userAnswer.getText().toString()).equals(answerText)) {
            count.cancel();
            saveScore();

            finish();
            overridePendingTransition(0, 0);
            startActivity(new Intent(quizPage.this, losePage.class));
            overridePendingTransition(0, 0);
        }
    }

    public void onBackPressed(){
        count.cancel();

        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(quizPage.this, MainActivity.class));
        overridePendingTransition(0, 0);
    }

    public void saveScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SCORE_TEXT, scoreText.getText().toString());

        editor.apply();
    }

    public void loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        load_score = sharedPreferences.getString(SCORE_TEXT, "");
    }

    public void updateScore(){
        scoreText.setText(load_score);
        score = Integer.parseInt(scoreText.getText().toString());
    }
}
