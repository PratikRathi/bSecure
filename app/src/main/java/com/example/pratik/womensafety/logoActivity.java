package com.example.pratik.womensafety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class logoActivity extends AppCompatActivity {
    private  static int SPLASH_TIME_OUT=1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home=new Intent(logoActivity.this,IntroActivity.class);
                startActivity(home);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
