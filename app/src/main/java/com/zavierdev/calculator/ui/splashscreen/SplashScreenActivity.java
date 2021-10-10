package com.zavierdev.calculator.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zavierdev.calculator.ui.home.MainActivity;
import com.zavierdev.calculator.R;

public class SplashScreenActivity extends AppCompatActivity {
    private int DELAY_MILIS = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_MILIS);
    }
}