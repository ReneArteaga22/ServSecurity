package com.example.servidores.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitcuartos.models.SensorList;
import com.example.retrofitcuartos.request.RequestSensors;
import com.example.retrofitcuartos.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SensorsRepository {
    public Retrofit retrofit;
    void setRetrofit() {
        retrofit = RetrofitClient.getRetrofitClient();
    }
    public MutableLiveData<SensorList> getSensors(String idcuarto) {
        setRetrofit();
        RequestSensors sensorsreq = retrofit.create(RequestSensors.class);
        Call<SensorList> sensCall = sensorsreq.getSensores(idcuarto);
        MutableLiveData<SensorList> mutableLiveData = new MutableLiveData<>();

        sensCall.enqueue(new Callback<SensorList>() {
            @Override
            public void onResponse(Call<SensorList> call, Response<SensorList> response) {
                SensorList sensorList;
            }

            @Override
            public void onFailure(Call<SensorList> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
