package com.badgamestudio.impossiblemathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    TextView highScoreScore;
    ImageView goldenStar_mainMenu;
    AdView mainMenu_adView;

    public static final String SHARED_PREFS = "sharedPrefs";

    private String load_high_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mainMenu_adView = findViewById(R.id.mainMenu_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mainMenu_adView.loadAd(adRequest);

        highScoreScore = findViewById(R.id.highScoreScore);
        goldenStar_mainMenu = findViewById(R.id.goldenStar_mainMenu);

        loadHighScore();
        updateHighScore();

        if (highScoreScore.getText().toString().equals("1000")){
            goldenStar_mainMenu.setVisibility(View.VISIBLE);
        }

    }

    public void resetScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(quizPage.SCORE_TEXT, "0");

        editor.apply();
    }

    public void startButton (View view){
        startActivity(new Intent(MainActivity.this, quizPage.class));
        overridePendingTransition(0, 0);
        resetScore();
    }

    public void loadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        load_high_score = sharedPreferences.getString(losePage.BEST_SCORE, "0");
    }

    public void updateHighScore(){
        highScoreScore.setText(load_high_score);
    }

}