package com.example.servidores.retrofit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getInstance()
    {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://54.161.51.54:80/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d(TAG, "Retrofit instance created");
        }

        return retrofit;
    }

}
