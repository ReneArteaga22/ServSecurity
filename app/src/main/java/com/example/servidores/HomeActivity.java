package com.example.servidores;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servidores.CrearCuarto.CrearcuartoActivity;
import com.example.servidores.adapters.CuartosAdapter;

import com.example.servidores.data.model.TokenGet;
import com.example.servidores.models.Cuartos;
import com.example.servidores.request.ApiInterface;
import com.example.servidores.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rc;
    ProgressBar pb;
    LinearLayoutManager llm;
    CuartosAdapter Ca;
    String token;
    List<Cuartos> cuartosList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);


        token = TokenGet.getTokenFromSharedPreferences(this);
        Log.d("Token Value", "Token: " + token);
        rc = findViewById(R.id.recyclerView);
        pb = findViewById(R.id.progressBar);
        llm = new LinearLayoutManager(this);
        rc.setLayoutManager(llm);
        Ca = new CuartosAdapter(cuartosList);
        rc.setAdapter(Ca);
        fetchCuartos();
    }

    private void fetchCuartos() {
       pb.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = RetrofitClient.getInstance().create(ApiInterface.class);
        Log.d("Token Value Dentro", "Tokendent: " + token);
        Call<List<Cuartos>> call = apiInterface.getCuartos("Bearer "+token);
        call.enqueue(new Callback<List<Cuartos>>() {
            @Override
            public void onResponse(Call<List<Cuartos>> call, Response<List<Cuartos>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    cuartosList.addAll(response.body());
                    Ca.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<List<Cuartos>> call, Throwable t) {
                pb.setVisibility(View.GONE);
                Toast.makeText(com.example.servidores.HomeActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
    public void ayuda(View v)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HomeActivity.this, UsuariosVista.class);
                startActivity(intent);

            }
        }, 500); // 5000 milisegundos (5 segundos)
    }
    public void agregar(View view) {
        Intent intent = new Intent(this, CrearcuartoActivity.class);
        startActivity(intent);
    }
}

