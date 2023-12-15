package com.example.servidores.ui.register;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.servidores.R;
import com.example.servidores.data.model.RegUser;
import com.example.servidores.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etLastName,etEmail, etPassword, etConfirmPassword;
    private TextView iniciaSesion;
    private Button btnRegister;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etName = findViewById(R.id.editTextName);
        etLastName =findViewById(R.id.editTextLastName);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etConfirmPassword = findViewById(R.id.editTextConfirmPwd);
        btnRegister = findViewById(R.id.buttonRegister);


        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        btnRegister.setOnClickListener(view -> {
            String name = etName.getText().toString();
            String lastname = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String password_confirmation= etConfirmPassword.getText().toString();

            clearErrors();

            RegUser reguser = new RegUser();
            reguser.setName(name);
            reguser.setLastname(lastname);
            reguser.setEmail(email);
            reguser.setPassword(password);
            reguser.setPassword_confirmation(password_confirmation);


            if (reguser.isValid()) {
                disableEditTextFocus();
                registerViewModel.registerUser(reguser).observe(this, result -> {
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                    // Accede a la información del usuario si el registro fue exitoso
                    if (result.equals("Usuario creado correctamente")) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else {
                        enableEditTextFocus();
                    }
                });
            } else {
                enableEditTextFocus();
                showValidationErrors(reguser);
            }
        });
    }

    private void clearErrors() {
        etName.setError(null);
        etLastName.setError(null);
        etEmail.setError(null);
        etPassword.setError(null);
        etConfirmPassword.setError(null);
    }

    private void showValidationErrors(RegUser reguser) {
        if (!reguser.isNombreValid()) {
            etName.setError("Campo requerido");
        }

        if (!reguser.isEmailValid()) {
            etEmail.setError("Correo electrónico no válido");
        }

        if (!reguser.isPasswordValid()) {
            etPassword.setError("Campo requerido");
        }

        if (!reguser.isConfirmPasswordValid()) {
            etConfirmPassword.setError("Las contraseñas no coinciden");
        }
    }
    private void disableEditTextFocus() {
        etName.setFocusable(false);
        etEmail.setFocusable(false);
        etPassword.setFocusable(false);
        etConfirmPassword.setFocusable(false);

        etName.invalidate();
        etEmail.invalidate();
        etPassword.invalidate();
        etConfirmPassword.invalidate();
    }

    private void enableEditTextFocus() {
        etName.setFocusable(true);
        etEmail.setFocusable(true);
        etPassword.setFocusable(true);
        etConfirmPassword.setFocusable(true);

        etName.setFocusableInTouchMode(true);
        etEmail.setFocusableInTouchMode(true);
        etPassword.setFocusableInTouchMode(true);
        etConfirmPassword.setFocusableInTouchMode(true);

        etName.invalidate();
        etEmail.invalidate();
        etPassword.invalidate();
        etConfirmPassword.invalidate();
    }
}
