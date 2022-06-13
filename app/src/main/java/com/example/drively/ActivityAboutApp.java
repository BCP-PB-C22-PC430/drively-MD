package com.example.drively;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ActivityAboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutapp);

        //configuring collapsing toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("About App");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.not_black));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.not_black));

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
            startActivity(new Intent(getApplicationContext(), ActivitySignIn.class));
//            Toast.makeText(this, "SIGN OUT is under develop.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}