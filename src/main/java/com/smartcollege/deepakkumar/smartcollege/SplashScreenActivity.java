package com.smartcollege.deepakkumar.smartcollege;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent obj = new Intent(SplashScreenActivity.this, FirstActivity.class);
                    startActivity(obj);
                }
            }
        };
        th.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

