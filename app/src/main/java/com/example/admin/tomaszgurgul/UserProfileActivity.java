package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileActivity extends AppCompatActivity {
    Button logoutButton;
    RecyclerView recyclerView;
    List<Array> jsonLists;
    RecyclerViewAdapter recyclerViewAdapter;
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

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Array> call = apiInterface.getList();
      call.enqueue(new Callback<Array>() {
          @Override
          public void onResponse(Call<Array> call, Response<Array> response) {

              if (response.isSuccessful()) {

                 List<Array> arrays= response.body().getArray();
                 recyclerView = (RecyclerView) findViewById(R.id.jsonListRecyclerView);
                  LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                  recyclerView.setLayoutManager(layoutManager);
                  recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), arrays);
                    recyclerView.setAdapter(recyclerViewAdapter);


              }
          }

          @Override
          public void onFailure(Call<Array> call, Throwable t) {
              Toast.makeText(UserProfileActivity.this, "Error...", Toast.LENGTH_SHORT).show();
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
