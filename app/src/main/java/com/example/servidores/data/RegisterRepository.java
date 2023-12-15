package com.example.servidores.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.servidores.data.model.RegUser;
import com.example.servidores.data.model.RegisterResponse;
import com.example.servidores.retrofit.RegisterRequest;
import com.example.servidores.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    private RegisterRequest apiService;

    public RegisterRepository() {
        apiService = RetrofitClient.getInstance().create(RegisterRequest.class);
    }

    public LiveData<String> registerUser(RegUser reguser) {
        MutableLiveData<String> registerResult = new MutableLiveData<>();

        Call<RegisterResponse> call = apiService.registerUser(reguser);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().getMessage();
                    registerResult.setValue(message);
                } else {
                    registerResult.setValue("Error en el registro");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerResult.setValue("Error de red: " + t.getMessage());
            }

        });

        return registerResult;
    }
}

