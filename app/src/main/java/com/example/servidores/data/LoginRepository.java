package com.example.servidores.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.servidores.data.model.LogUser;
import com.example.servidores.data.model.LoginResponse;
import com.example.servidores.retrofit.LoginRequest;
import com.example.servidores.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class LoginRepository {
    private static final String TAG = LoginRepository.class.getSimpleName();
    private LoginRequest loginRequest;
    private Context context;

    public LoginRepository(Context context) {
        this.context = context;
        loginRequest = RetrofitClient.getInstance().create(LoginRequest.class);
    }

    public void getUser(String email, String password, MutableLiveData<LoginResponse> loginResponseLiveData) {
        Log.d(TAG, "getUser called");
        loginRequest.login(new LogUser(email, password)).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse != null && loginResponse.getAccess_token() != null) {
                        // Token exitoso, guárdalo y notifica al ViewModel
                        saveTokenToSharedPreferences(loginResponse.getAccess_token());
                        loginResponseLiveData.setValue(loginResponse);
                    } else {
                        // Error en la respuesta del servidor, notificar al ViewModel
                        handleErrorResponse(new UnknownError("Invalid server response"), loginResponseLiveData);
                    }
                } else {
                    // Error en la respuesta del servidor, notificar al ViewModel
                    handleErrorResponse(new HttpException(response), loginResponseLiveData);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "Error in Retrofit call", t);
                handleErrorResponse(t, loginResponseLiveData);
            }
        });
    }

    private void handleErrorResponse(Throwable t, MutableLiveData<LoginResponse> loginResponseLiveData) {
        Log.d(TAG, "Error en la solicitud: " + (t != null ? t.getMessage() : "Unknown error"));
        LoginResponse loginResponse = new LoginResponse();

        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            if (httpException.code() == 401) {
                loginResponse.setErrorType("unauthorized");
            } else {
                loginResponse.setErrorType("network");
            }
        } else {
            loginResponse.setErrorType("network");
        }

        loginResponseLiveData.setValue(loginResponse);
    }

    private void saveTokenToSharedPreferences(String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }
}
