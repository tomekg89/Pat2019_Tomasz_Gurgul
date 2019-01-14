package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class HomeActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            //    "(?=.*[a-zA-Z])" +      //any letter
            //    "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +               //at least 4 characters
            "$");

    Button LoginBtn;
    EditText emailEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LoginBtn = (Button) findViewById(R.id.loginButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        // login button and intent to user profile
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmail();
                checkPassword();
                Login();

            }
        });
    }

    // check if email is correct
    private boolean checkEmail() {
        String emailInput = emailEditText.getText().toString().trim();
        if (emailInput.isEmpty()) {
            emailEditText.setError("Email can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailEditText.setError("Please check if email adress is correct");
            return false;
        } else {
            emailEditText.setError(null);
            return true;
        }
    }

    // check if password is strong enought
    private boolean checkPassword() {
        String passwordInput = passwordEditText.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            passwordEditText.setError("Password can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordEditText.setError("Password must have at least 8 characters with at least one Capital letter and one number!");
            return false;
        } else {
            passwordEditText.setError(null);
            return true;
        }
    }

    // check if email and password are true, save sharedprefs, intent to  UserProfileActivity
    private void Login() {
        if (checkEmail() && checkPassword() == true) {
            Intent userProfileIntent = new Intent(HomeActivity.this, UserProfileActivity.class);
            startActivity(userProfileIntent);
            finish();
            SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("loggedin", "yes");
            editor.apply();

        } else {
            Toast.makeText(this, "Something is wrong :)", Toast.LENGTH_LONG).show();
        }
    }

    //close app on back button pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
