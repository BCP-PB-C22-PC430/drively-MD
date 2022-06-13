package com.example.drively;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityDrowsiness extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drowsiness);
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
