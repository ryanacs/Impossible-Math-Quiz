package com.badgamestudio.impossiblemathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class winPage extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";

    AdView winPage_adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_page);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        winPage_adView = findViewById(R.id.winPage_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        winPage_adView.loadAd(adRequest);

    }

    public void returnMainMenu_win (View view){
        loadWin();

        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(winPage.this, MainActivity.class));
        overridePendingTransition(0, 0);
    }

    public void onBackPressed(){
        loadWin();

        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(winPage.this, MainActivity.class));
        overridePendingTransition(0, 0);
    }

    public void loadWin(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(losePage.BEST_SCORE, "1000");

        editor.apply();
    }

}