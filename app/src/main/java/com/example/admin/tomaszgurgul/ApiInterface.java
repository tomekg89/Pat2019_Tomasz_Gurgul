package com.example.admin.tomaszgurgul;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("za8z4")
    Call<Array>getList();
}
