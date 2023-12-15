package com.example.servidores.retrofit;

import com.example.servidores.data.model.CrearCuartoResponse;
import com.example.servidores.data.model.CrearCuartoUser;
import com.example.servidores.data.model.LogUser;
import com.example.servidores.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CrearCuartoRequest {
    @POST("auth/regcuarto")
    Call<CrearCuartoResponse> createcuarto(
            @Header("Authorization") String authorizationHeader,
            @Body CrearCuartoUser crearCuartoUser
    );
}