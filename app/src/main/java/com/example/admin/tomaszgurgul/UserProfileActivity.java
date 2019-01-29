package com.example.admin.tomaszgurgul;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileActivity extends AppCompatActivity {
    public final static String BASE_SERVER_URL = "http://192.168.0.1:8080";
    RecyclerView recyclerView;
    List<Array.Array_> arrays;
    RecyclerViewAdapter recyclerViewAdapter;
    ProgressBar progressBar;
    IsLogged isLogged;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //Retrofit api client
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.myjson.com/bins/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    ApiInterface apiInterface = retrofit.create(ApiInterface.class);
    Call<Array> call = apiInterface.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        recyclerView = (RecyclerView) findViewById(R.id.jsonListRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //fab - logout clear sharedprefs
        findViewById(R.id.logoutFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                isLogged = new IsLogged(preferences);
                isLogged.clearSharedPrefs();
                Intent loginScreen = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(loginScreen);
                finish();
            }
        });
        showJson();
    }

    public void showJson() {
        call.enqueue(new Callback<Array>() {
            @Override
            public void onResponse(Call<Array> call, Response<Array> response) {
                if (response.isSuccessful()) {
                    arrays = response.body().getArray();
                    recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), arrays);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Array> call, Throwable t) {
                Toast.makeText(UserProfileActivity.this, "Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
