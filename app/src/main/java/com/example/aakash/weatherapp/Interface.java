package com.example.aakash.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Interface {
    @GET("2.5/weather")
    Call<Example> requestResponse(@Query("q") String q, @Query("appid") String appid);
}
