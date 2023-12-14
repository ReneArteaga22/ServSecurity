package com.example.servsec.servidores.retrofit.requests;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserInterface {
    @GET("/auth/profile")
    Call<Usuarios> obtenerUsuario();
}
