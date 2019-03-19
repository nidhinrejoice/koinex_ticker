package com.nidhin.koinexticker.api;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("ticker")
    Call<String> ticker();
}
