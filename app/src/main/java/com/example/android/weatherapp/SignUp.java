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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    TextView memail;
    TextView mpassword;
   Button msignUp;
    private FirebaseAuth mAuth;
    ProgressBar pr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();
        init();

        msignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Msignup");

                if (checkUser()) {
                    pr.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(memail.getText().toString(), mpassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    pr.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        Toast.makeText(SignUp.this, "User Login Sucess", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent();
                                        setResult(2, intent);
                                        finish();


                                    } else {

                                        Toast.makeText(SignUp.this, "LoginNotSucess" + task.getException(), Toast.LENGTH_LONG).show();


                                    }


                                }
                            });


                }
            }
        });







    }

    void init()
    {
      memail=findViewById(R.id.email);
      mpassword=findViewById(R.id.password);
      msignUp=findViewById(R.id.signupbutton);
      pr=findViewById(R.id.progrssbarinsignup);




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


        if(mpassword.getText().toString().isEmpty())
        {
            mpassword.setError("Password Is required");
            mpassword.requestFocus();
            return false;


        }


        return true;




    }
}
