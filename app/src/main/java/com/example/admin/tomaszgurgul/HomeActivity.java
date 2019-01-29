package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    RecyclerView recyclerView;
    IsLogged isLogged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.jsonListRecyclerView);
        setContentView(R.layout.activity_home);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatorHelper.checkEmail(emailEditText);
                ValidatorHelper.checkPassword(passwordEditText);
                login();
            }
        });
    }
    private void login() {
        if (ValidatorHelper.checkEmail(emailEditText) && ValidatorHelper.checkPassword(passwordEditText)) {
            Intent userProfileIntent = new Intent(HomeActivity.this, UserProfileActivity.class);
            startActivity(userProfileIntent);
            finish();
            SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
            isLogged = new IsLogged(preferences);
            isLogged.saveSharedPrefs();
        } else {
            Toast.makeText(this, "Something is wrong :)", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
