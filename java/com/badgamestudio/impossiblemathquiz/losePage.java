package com.badgamestudio.impossiblemathquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class losePage extends AppCompatActivity {

    TextView scoreText;
    TextView highScoreScore;
    AdView losePage_adView;

    int score_int;
    int highScore_int = 0;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BEST_SCORE = "best_score";

    private String load_score_lose;
    private String load_best_score;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose_page);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3392836502986580/2679589258", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.d("TAG", "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d("TAG", "Ad dismissed fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e("TAG", "Ad failed to show fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded for an ad.
                                Log.d("TAG", "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d("TAG", "Ad showed fullscreen content.");
                            }
                        });

                        if (mInterstitialAd != null && score_int >= 20) {
                            mInterstitialAd.show(losePage.this);
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("TAG", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });



        losePage_adView = findViewById(R.id.losePage_adView);
        losePage_adView.loadAd(adRequest);

        scoreText = findViewById(R.id.scoreText);
        highScoreScore = findViewById(R.id.highScoreScore);

        score_int = Integer.parseInt(scoreText.getText().toString());
        highScore_int = Integer.parseInt(highScoreScore.getText().toString());

        loadScore_lose();
        updateScore_lose();

        loadBestScore();
        updateBestScore();

        if ((score_int = Integer.parseInt(scoreText.getText().toString())) >= (highScore_int = Integer.parseInt(highScoreScore.getText().toString()))){
            highScoreScore.setText("" + score_int);
            saveBestScore();
        }

    }

    public void returnMainMenu_lose (View view){
        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(losePage.this, MainActivity.class));
        overridePendingTransition(0, 0);
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(losePage.this, MainActivity.class));
        overridePendingTransition(0, 0);
    }

    public void loadScore_lose(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        load_score_lose = sharedPreferences.getString(quizPage.SCORE_TEXT, "");
    }

    public void updateScore_lose(){
        scoreText.setText(load_score_lose);
    }

    public void saveBestScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(BEST_SCORE, highScoreScore.getText().toString());

        editor.apply();
    }

    public void loadBestScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        load_best_score = sharedPreferences.getString(BEST_SCORE, "0");
    }

    public void updateBestScore(){
        highScoreScore.setText(load_best_score);
    }

}