package com.example.servidores.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.servidores.models.SensorList;
import com.example.servidores.models.Sensores;
import com.example.servidores.request.RequestSensors;
import com.example.servidores.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SensorsRepository {
    public Retrofit retrofit;
    void setRetrofit() {
        retrofit = RetrofitClient.getInstance();
    }
    public MutableLiveData<List<Sensores>> getSensors(String idcuarto) {
        setRetrofit();
        RequestSensors sensorsreq = retrofit.create(RequestSensors.class);
        Call<List<Sensores>> sensCall = sensorsreq.getSensores(idcuarto);
        MutableLiveData<List<Sensores>> mutableLiveData = new MutableLiveData<>();

        sensCall.enqueue(new Callback<List<Sensores>>() {
            @Override
            public void onResponse(Call<List<Sensores>> call, Response<List<Sensores>> response) {
                List<Sensores> senss = response.body();
                mutableLiveData.setValue(senss);
            }

            @Override
            public void onFailure(Call<List<Sensores>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
