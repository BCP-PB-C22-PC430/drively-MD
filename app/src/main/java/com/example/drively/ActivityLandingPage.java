package com.example.drively;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLandingPage extends AppCompatActivity {

    Button signin, signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        signin = findViewById(R.id.lpsignin);
        signup = findViewById(R.id.lpsignup);

        signin.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivitySignIn.class)));

        signup.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivitySignUp.class)));
    }
}
