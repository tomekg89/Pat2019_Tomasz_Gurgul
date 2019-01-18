package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {
    Button logoutButton;
    //close app on back button pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Intent loginScreen = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(loginScreen);
                finish();
            }
        });
    }
    // logout user - clear sharedpreferences
    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
