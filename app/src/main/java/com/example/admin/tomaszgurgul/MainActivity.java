package com.example.admin.tomaszgurgul;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int spash_time = 5000;
    private Handler handler;

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        Toast.makeText(this, "Intent anulowany", Toast.LENGTH_LONG).show();
    }

    public void splashScreen(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashScreen();
            }
        }, spash_time);

    }
}
