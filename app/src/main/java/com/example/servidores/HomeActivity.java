package com.example.servidores;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servidores.adapters.CuartosAdapter;

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
    List<Cuartos> cuartosList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        rc = findViewById(R.id.recyclerView);
        pb = findViewById(R.id.progressBar);
        llm = new LinearLayoutManager(this);
        rc.setLayoutManager(llm);
        Ca = new CuartosAdapter(cuartosList);
        rc.setAdapter(Ca);
        fetchCuartos();
    }

    private void fetchCuartos() {
        String key = " Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vNTQuMTYxLjUxLjU0L2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAyNjA1ODAyLCJleHAiOjE3MDI2MDk0MDIsIm5iZiI6MTcwMjYwNTgwMiwianRpIjoiVjFySEthVEM5bzdOMTBnaSIsInN1YiI6IjEiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.oxvYL0YFZMcyi9qBp0wCPRBrI7dEDNsKaBwCRZQNua0";
        pb.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = RetrofitClient.getInstance().create(ApiInterface.class);
        Call<List<Cuartos>> call = apiInterface.getCuartos(key);
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
    public void ayuda()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HomeActivity.this, UsuariosVista.class);
                startActivity(intent);

            }
        }, 500); // 5000 milisegundos (5 segundos)
    }
}

