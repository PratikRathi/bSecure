package com.example.pratik.womensafety;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AccountActivity extends AppCompatActivity implements View.OnClickListener {


    TextView t;
    TextView g;
    private Button mLogout;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();







        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,MainActivity.class));

        }

        FirebaseUser user =mAuth.getCurrentUser();

         t = (TextView) findViewById(R.id.intro);
        Typeface myCustomFont= Typeface.createFromAsset(getAssets(), "fonts/Ostrichfontd.otf");
        t.setTypeface(myCustomFont);

        g= (TextView) findViewById(R.id.welcome);
        g.setTypeface(myCustomFont);

        g.setText("WELCOME " + user.getEmail());

        mLogout = (Button) findViewById(R.id.logoutBtn);

        mLogout.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v == mLogout){

            mAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
