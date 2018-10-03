package com.companyname.basisfitnesstest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Thread activityThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        activityThread.start();

    }
}
