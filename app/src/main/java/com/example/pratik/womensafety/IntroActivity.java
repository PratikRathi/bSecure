package com.example.pratik.womensafety;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.logging.Handler;

public class IntroActivity extends Activity {
    private ViewPager mviewPager;
    private LinearLayout mlinearLayout;

    private SliderAdapter sliderAdapter;

    private TextView[] mDot;

    private Button mBackBtn;
    private Button mNextBtn;
    private ProgressDialog progressDialog;
    private int mCurrentPage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        progressDialog = new ProgressDialog(this);
        mviewPager = (ViewPager) findViewById(R.id.view_pager);
        mlinearLayout = (LinearLayout)findViewById(R.id.linear_layout);

        mBackBtn = (Button) findViewById(R.id.prevBtn);
        mNextBtn = (Button) findViewById(R.id.nextBtn);

        sliderAdapter = new SliderAdapter(this);
        mviewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);


        mviewPager.addOnPageChangeListener(viewListner);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCurrentPage == 2)
                {

                    progressDialog.setMessage("Forwading...");
                    progressDialog.show();

                    startActivity(new Intent(IntroActivity.this, login.class));

                }
                else if(mCurrentPage == 3){

                    Intent myIntent = new Intent(Intent.ACTION_SEND);
                    final  String appPackageName =getApplicationContext().getPackageName();
                     String strAppLink = "";
                    try
                    {
                        strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
                    }
                    catch(android.content.ActivityNotFoundException anfe)
                    {

                        strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
                    }
                    myIntent.setType("text/plain");
                    String shareBody ="Download " + strAppLink;
                    String shareSub = "APP NAME/TITLE";
                    myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                    myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(myIntent,"Share using"));
                }
                else{
                    mviewPager.setCurrentItem(mCurrentPage + 1);
                }
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mviewPager.setCurrentItem(mCurrentPage-1);
            }
        });

    }
    public void addDotsIndicator(int position){

        mDot = new TextView[4];
        mlinearLayout.removeAllViews();
        for(int i=0;i<mDot.length;i++){

            mDot[i]=new TextView(this);
            mDot[i].setTextSize(35);
            mDot[i].setText(Html.fromHtml("&#8226;"));
            mDot[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mlinearLayout.addView(mDot[i]);
        }
        if(mDot.length > 0)
        {

            mDot[position].setTextColor(getResources().getColor(R.color.bluelightish));
        }
    }

    ViewPager.OnPageChangeListener viewListner =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;
            if(i == 0)
            {

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");
            }
            else if(i == mDot.length - 2){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Register");
                mBackBtn.setText("Back");

            }
            else if(i == mDot.length-1){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Share");
                mBackBtn.setText("Back");
            }
            else
            {

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
