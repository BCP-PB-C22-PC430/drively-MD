package com.example.drively;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignUp extends AppCompatActivity {

    EditText etemail, etpw;
    Button signup;
    TextView signin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etemail = findViewById(R.id.signup_email);
        etpw = findViewById(R.id.signup_pw);
        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.tv_signinpage);
        progressBar = findViewById(R.id.progressbarSignup);

        signup.setOnClickListener(v -> {
            //go to method regist below this oncreate method
            signUp();
        });

        signin.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivitySignIn.class)));


    }

    private void signUp() {
        final String email = etemail.getText().toString(),
                pw = etpw.getText().toString();

        if (email.isEmpty()) {
            etemail.setError("Email harus diisi!");
            etemail.requestFocus();
            return;
        }

        if (pw.isEmpty()) {
            etpw.setError("Password harus diisi!");
            etpw.requestFocus();
            return;
        }

        if (email.equals("admin") && pw.equals("admin")) {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(ActivitySignUp.this, "Regist berhasil.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));

        }

    }
}
