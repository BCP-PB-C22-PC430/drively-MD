package com.example.drively;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class ActivitySignIn extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText etemail, etpw;
    Button signin, google;
    TextView signup;
    ProgressBar progressBar;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    FirebaseAuth firebaseAuth;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etemail = findViewById(R.id.signin_email);
        etpw = findViewById(R.id.signin_pw);
        signin = findViewById(R.id.btnsignin);
        google = findViewById(R.id.btngooglesignin);
        signup = findViewById(R.id.tv_signup);
        progressBar = findViewById(R.id.progressbarSignin);

        firebaseAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(v -> {
            //go to method login below this oncreate method
            signinUser();
        });

        signup.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ActivitySignUp.class));
//                Toast.makeText(ActivitySignIn.this, "Under Develop.", Toast.LENGTH_SHORT).show();
        });


//        google sign in
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        google.setOnClickListener(v -> {
            signIn();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));
        }
    }

    private void signinUser() {
        String email = etemail.getText().toString(),
                pw = etpw.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pw)) {
            etemail.setError("Email harus diisi!");
            etpw.setError("Password harus diisi!");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


    }

    //GOOGLE AUTH
    //signin method that contain intent to google signin
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 007);
    }

    //method result from signin()\
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 007) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));
                            finish();
                            Toast.makeText(ActivitySignIn.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(ActivitySignIn.this, "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
