package com.example.pratik.womensafety;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class slideActivity extends AppCompatActivity {


    TextView a;
    TextView b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        a= (TextView) findViewById(R.id.slide_desc);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Ostrichfontd.otf");
        a.setTypeface(myCustomFont);

        b=(TextView) findViewById(R.id.slide_heading);
        b.setTypeface(myCustomFont);
    }
}
