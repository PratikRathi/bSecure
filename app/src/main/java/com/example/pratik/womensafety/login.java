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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {

        private Button BtnRegister;
        private EditText email;
        private EditText password;
        private TextView signin;
        private  TextView userReg;
        private  TextView mText11;
        private  TextView mText22;
        private ProgressDialog progressDialog;
        private FirebaseAuth firebaseAuth;
        private FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null){
                    finish();
                    startActivity(new Intent(login.this, AccountActivity.class));
                }
            }
        };
        mText11 = (TextView) findViewById(R.id.textView2);
        mText22 = (TextView) findViewById(R.id.textView3);
        userReg = (TextView) findViewById(R.id.descLogin);
        BtnRegister = (Button) findViewById(R.id.register1);
        email = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        signin = (TextView) findViewById(R.id.textView);

        BtnRegister.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private  void  RegisterUser(){

        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)){
            Toast.makeText(login.this,"Fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(login.this,"User Registered Successfully.",Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(login.this,"Could not Register \nPlease try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v == BtnRegister){
            RegisterUser();
        }
        if(v == signin){
            finish();
            startActivity(new Intent(login.this, MainActivity.class));

        }
    }
}
