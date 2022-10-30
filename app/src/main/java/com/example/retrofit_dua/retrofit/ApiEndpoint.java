package com.example.retrofit_dua.retrofit;

import com.example.retrofit_dua.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("data.php")
    Call<MainModel> getData();
}
