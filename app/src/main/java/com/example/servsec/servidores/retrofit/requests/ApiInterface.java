package com.example.servsec.servidores.retrofit.requests;

import com.example.servidores.data.model.Cuartos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/auth/recibir-datos")
    Call<List<Cuartos>> getCuartos();

}
