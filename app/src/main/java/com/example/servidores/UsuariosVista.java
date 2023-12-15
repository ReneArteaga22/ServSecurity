package com.example.servidores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servidores.data.model.TokenGet;
import com.example.servidores.models.Usuarios;
import com.example.servidores.request.ApiInterface;
import com.example.servidores.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuariosVista extends AppCompatActivity {
    TextView textViewDatosUsuario ;
    TextView  textViewDatosApellido ;
    TextView textViewDatosCorreo ;
    String token;
    Retrofit retro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_vista);
        token = TokenGet.getTokenFromSharedPreferences(this);
        textViewDatosUsuario = findViewById(R.id.datosusuario);
        textViewDatosApellido = findViewById(R.id.datosapellido);
        textViewDatosCorreo = findViewById(R.id.datoscorreo);

        retro = new Retrofit.Builder()
                .baseUrl("http://54.161.51.54:80/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface user = retro.create(ApiInterface.class);
        Call<Usuarios> call = user.obtenerUsuario("Bearer "+token);
        ayuda(call);  // Pasa la llamada como parámetro al método ayuda
    }

    protected void ayuda(Call<Usuarios> call) {
        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (response.isSuccessful()) {
                    Usuarios usuario = response.body();
                    // Actualiza tu TextView con los datos del usuario
                    textViewDatosUsuario.setText(usuario.getName());
                    textViewDatosApellido.setText(usuario.getLastname());
                    textViewDatosCorreo.setText(usuario.getEmail());
                } else {
                    // Maneja la respuesta de error
                }
            }


            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                // Maneja el error de la llamada
            }
        });
    }
    public void Loggout(View v)
    {
        textViewDatosUsuario.setText("");
        textViewDatosApellido.setText("");
        textViewDatosCorreo.setText("");
        TokenGet.removeTokenFromSharedPreferences(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}