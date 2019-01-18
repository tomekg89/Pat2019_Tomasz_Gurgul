package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int spash_time = 5000;
    private Handler handler;

// cancel splash screen on back button pressed
    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
    }
    public void splashScreen() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkIfLogged();
    }
// check if user is already logged in - if yes intent to UserProfileActivity, else splash screen and HomeActivity
    private void checkIfLogged() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String loggedIn = sharedPreferences.getString("loggedin", "");

        if (loggedIn.equals("yes")) {
            Intent userProfileIntent = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(userProfileIntent);
            finish();
            Toast.makeText(this, "User already logged in", Toast.LENGTH_LONG).show();

        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    splashScreen();
                }
            }, spash_time);

        }

    }
}





