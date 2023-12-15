package com.example.servidores.retrofit;


import com.example.servidores.data.model.RegUser;
import com.example.servidores.data.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

    public interface RegisterRequest {
        @POST("/api/auth/register")
        Call<RegisterResponse> registerUser(@Body RegUser RegUser);
    }


