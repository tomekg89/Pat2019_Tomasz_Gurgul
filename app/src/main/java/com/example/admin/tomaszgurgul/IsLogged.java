package com.example.admin.tomaszgurgul;

import android.content.SharedPreferences;

public class IsLogged {
    private final SharedPreferences preferences;

    public IsLogged(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveSharedPrefs() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("loggedin", true);
        editor.apply();
    }

    public void clearSharedPrefs(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("loggedin", false);
        editor.apply();
    }
}
