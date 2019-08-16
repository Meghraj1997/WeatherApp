package com.example.android.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    MaterialButton login;
    Button msignUp;
    TextView memail,mpass;
    private FirebaseAuth mAuth;
    ProgressBar pr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        init();



            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("TAg", "USER");

                    if (checkUser()) {

                        pr.setVisibility(View.VISIBLE);

                        mAuth.signInWithEmailAndPassword(memail.getText().toString(), mpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                pr.setVisibility(View.GONE);

                                if (task.isSuccessful()) {

                                    Toast.makeText(Login.this, "Login,Sucess", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login.this, Weatherfeed.class);
                                    startActivity(intent);



                                } else {


                                    Toast.makeText(Login.this, " " + task.getException().getMessage(), Toast.LENGTH_LONG).show();


                                }


                            }
                        });


                    }
                }
            });




         msignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent=new Intent(Login.this,SignUp.class);
                 startActivity(intent);



             }
         });




    }

    void init()
    {
        memail=findViewById(R.id.email_id);
        mpass=findViewById(R.id.Password);
        mAuth = FirebaseAuth.getInstance();
        pr=findViewById(R.id.progressbar);

        login=findViewById(R.id.login);
        msignUp=findViewById(R.id.signupbtn);

    }


   boolean checkUser()
    {
        if(memail.getText().toString().isEmpty())
        {

            memail.setError("Email IS Required");
            memail.requestFocus();
            return false;
        }




        if(!Patterns.EMAIL_ADDRESS.matcher(memail.getText().toString()).matches())
        {

        memail.setError("Please Enter Valid Email ");
        memail.requestFocus();
        return false;

        }


        if(mpass.getText().toString().isEmpty())
        {
            mpass.setError("Password Is required");
            mpass.requestFocus();
            return false;


        }


    return true;




    }
}
