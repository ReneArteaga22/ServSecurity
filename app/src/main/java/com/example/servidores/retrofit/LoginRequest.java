package com.example.servidores.retrofit;

import com.example.servidores.data.model.LogUser;
import com.example.servidores.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRequest {

    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body LogUser LogUser);
}
