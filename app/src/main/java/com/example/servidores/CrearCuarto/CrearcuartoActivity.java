package com.example.servidores.CrearCuarto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.servidores.HomeActivity;
import com.example.servidores.R;
import com.example.servidores.data.model.CrearCuartoUser;
import com.example.servidores.data.model.RegUser;
import com.example.servidores.data.model.TokenGet;
import com.example.servidores.ui.login.LoginActivity;
import com.example.servidores.ui.register.RegisterActivity;
import com.example.servidores.ui.register.RegisterViewModel;

public class CrearcuartoActivity extends AppCompatActivity {

    private CrearCuartoViewModel crearCuartoViewModel;
    private EditText editTxname;
    private Button btncuarto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearcuarto);


        crearCuartoViewModel = new ViewModelProvider(this).get(CrearCuartoViewModel.class);

        editTxname = findViewById(R.id.cuartoname);
        btncuarto = findViewById(R.id.rgcuarto);

        btncuarto.setOnClickListener(view -> {
            String name = editTxname.getText().toString();
            clearErrors();

            CrearCuartoUser crearCuartoUser = new CrearCuartoUser();
            crearCuartoUser.setNombre(name);

            if (crearCuartoUser.isValid()) {
                disableEditTextFocus();
                // Verifica si crearCuartoViewModel no es nulo antes de usarlo
                if (crearCuartoViewModel != null) {
                    crearCuartoViewModel.crearCuarto(this, crearCuartoUser).observe(this, result -> {
                        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                        if (result.equals("Cuarto creado correctamente")) {
                            Intent intent = new Intent(CrearcuartoActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            enableEditTextFocus();
                        }
                    });
                } else {

                    enableEditTextFocus();
                }
            } else {
                enableEditTextFocus();
                showValidationErrors(crearCuartoUser);
            }
        });
    }

    private void clearErrors() {
        editTxname.setError(null);
    }

    private void showValidationErrors(CrearCuartoUser crearCuartoUser) {
        if (!crearCuartoUser.isNombreValid()) {
            editTxname.setError("Campo requerido");
        }
    }

    private void disableEditTextFocus() {
        editTxname.setFocusable(false);
        editTxname.invalidate();
    }

    private void enableEditTextFocus() {
        editTxname.setFocusable(true);
        editTxname.setFocusableInTouchMode(true);
        editTxname.invalidate();
    }
}