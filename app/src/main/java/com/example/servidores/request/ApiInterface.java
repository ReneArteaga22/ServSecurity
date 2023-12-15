package com.example.servidores.request;

import com.example.retrofitcuartos.models.Cuartos;
import com.example.retrofitcuartos.models.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {
    @GET("auth/recibir-datos")
    Call<List<Cuartos>> getCuartos(@Header("Authorization") String authorizationHeader);
    @GET("auth/profile")
    Call<Usuarios> obtenerUsuario();
}
