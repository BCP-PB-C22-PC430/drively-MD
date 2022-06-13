package com.example.drively;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        //configuring collapsing toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Home");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.not_black));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.not_black));

        //creating event listener for clickable cardview
        CardView ngantuk = findViewById(R.id.menu_ngantuk);
        CardView limit = findViewById(R.id.menu_limit);
        CardView prep = findViewById(R.id.menu_prep);

        ngantuk.setOnClickListener(v -> {
//                startActivity(new Intent(getApplicationContext(), ActivitySignUp.class));
            Toast.makeText(ActivityMainPage.this, "Ngantuk is under develop", Toast.LENGTH_SHORT).show();
        });

        limit.setOnClickListener(v -> {
                startActivity(new Intent(getApplicationContext(), ActivitySpeedLimit.class));
//            Toast.makeText(ActivityMainPage.this, "Limit is under develop", Toast.LENGTH_SHORT).show();
        });

        prep.setOnClickListener(v -> {
                startActivity(new Intent(getApplicationContext(), ActivityDrivePrep.class));
//            Toast.makeText(ActivityMainPage.this, "Prep is under develop", Toast.LENGTH_SHORT).show();
        });

        //configuring animation for main page
        Animation uptodown, downtoup;
        AppBarLayout abl;
        NestedScrollView nsv;
        abl = findViewById(R.id.appbar);
        nsv = findViewById(R.id.nestedscrollview);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        abl.setAnimation(uptodown);
        nsv.setAnimation(downtoup);

    }

    //        three dots
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.home) {
            startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));
//            Toast.makeText(this, "Home is under develop.", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.profile) {
            startActivity(new Intent(getApplicationContext(), ActivityProfile.class));
//            Toast.makeText(this, "Profile is under develop.", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.aboutapp) {
            startActivity(new Intent(getApplicationContext(), ActivityAboutApp.class));
//            Toast.makeText(this, "About App is under develop.", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.signout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Sure want to sign out?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getBaseContext(), ActivitySignIn.class));
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
