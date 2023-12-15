package com.example.servidores.CrearCuarto;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import static androidx.core.content.ContextCompat.startActivity;
import com.example.servidores.data.model.CrearCuartoResponse;
import com.example.servidores.data.model.CrearCuartoUser;
import com.example.servidores.data.model.RegUser;
import com.example.servidores.data.model.RegisterResponse;
import com.example.servidores.retrofit.CrearCuartoRequest;
import com.example.servidores.retrofit.RegisterRequest;
import com.example.servidores.retrofit.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearCuartoRepository {
    private CrearCuartoRequest apiServ;

    public CrearCuartoRepository() {
        apiServ = RetrofitClient.getInstance().create(CrearCuartoRequest.class);
    }

    public LiveData<String> crearCuarto(Context context, CrearCuartoUser crearCuartoUser) {
        MutableLiveData<String> registerResult = new MutableLiveData<>();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = preferences.getString("token", "");


        String authorizationHeader = "Bearer " + token;


        Call<CrearCuartoResponse> call = apiServ.createcuarto(authorizationHeader, crearCuartoUser);
        call.enqueue(new Callback<CrearCuartoResponse>() {
            @Override
            public void onResponse(Call<CrearCuartoResponse> call, Response<CrearCuartoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().getMsg();
                    registerResult.setValue("Cuarto creado");
                } else {
                    // Verifica el código de estado HTTP y muestra un mensaje específico
                    int statusCode = response.code();
                    String errorMessage = "Error en el registro del cuarto. Código: " + statusCode;

                    registerResult.setValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<CrearCuartoResponse> call, Throwable t) {
                t.printStackTrace();
                registerResult.setValue("Error de red: " + t.getMessage());


                if (call != null && call.request() != null && call.request().body() != null) {
                    System.out.println("Request Body: " + call.request().body().toString());
                }


                try {
                    if (call != null && call.execute() != null && call.execute().code() != 0) {
                        System.out.println("Response Code: " + call.execute().code());
                        // Imprime el cuerpo de la respuesta si está disponible
                        if (call.execute().errorBody() != null) {
                            System.out.println("Error Body: " + call.execute().errorBody().string());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        });

        return registerResult;
    }
}
