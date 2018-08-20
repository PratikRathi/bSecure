package com.example.pratik.womensafety;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mText;
    private TextView mText1;
    private TextView mText2;

    private Button mRegisterBtn;
    private TextView mLogin;

    private FirebaseAuth mAUth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);

        mEmailField = (EditText) findViewById(R.id.username_field);
        mPasswordField = (EditText) findViewById(R.id.password_field);
        mLogin = (TextView) findViewById(R.id.textView1);
        mText1 = (TextView) findViewById(R.id.textView2);
        mText2 = (TextView) findViewById(R.id.textView3);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);
        mText = (TextView) findViewById(R.id.desc);

        mAUth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null){
                    finish();
                    startActivity(new Intent(MainActivity.this, AccountActivity.class));
                }
            }
        };
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, login.class));
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        mAUth.addAuthStateListener(mAuthListner);
    }

    /**
     *
     */
    public void startSignIn(){



        String email=mEmailField.getText().toString();
        String password=mPasswordField.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_LONG).show();

        }
    else{
            progressDialog.setMessage("Signing in...");
            progressDialog.show();
            mAUth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Login Problem",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }



}
