package com.example.admin.tomaszgurgul;

import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ValidatorHelper {
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            //    "(?=.*[a-zA-Z])" +      //any letter
            //    "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +               //at least 4 characters
            "$");

    public static boolean checkEmail(EditText emailEditText) {
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
    public static boolean checkPassword(EditText passwordEditText) {
        Pattern PASSWORD_PATTERN = ValidatorHelper.PASSWORD_PATTERN;
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
}
