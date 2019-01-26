package com.example.admin.tomaszgurgul;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("lkqp0")
    Call<Array>getList();
}
