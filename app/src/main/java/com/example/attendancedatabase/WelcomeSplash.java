package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(WelcomeSplash.this, MainActivity.class);
            startActivity(intent);
            finish();
        },1500);
    }
}