package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static int spash_time = 5000;
    private Handler handler;

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

    private void checkIfLogged() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean("loggedin", false);
        if (isLogged) {
            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(intent);
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





