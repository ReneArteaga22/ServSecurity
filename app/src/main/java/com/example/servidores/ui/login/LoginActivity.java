package com.example.servidores.ui.login;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servidores.HomeActivity;
import com.example.servidores.R;
import com.example.servidores.ui.login.LoginViewModel;

import com.example.servidores.databinding.ActivityLoginBinding;
import com.example.servidores.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar vistas y ViewModel
        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);



        // Manejar el clic del botón de inicio de sesión
        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(view -> attemptLogin());
    }

    private void attemptLogin() {
        Log.d(TAG, "attemptLogin called");
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Verificar si los campos no están vacíos
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            // Manejar el caso en que los campos estén vacíos
            Toast.makeText(this, "Ingrese un correo electrónico y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "Before calling loginUser");
        loginViewModel.loginUser(email, password);
        // Observar los cambios en los datos del usuario
        loginViewModel.getUserResponseLiveData().observe(this, userResponse -> {
            if (userResponse != null) {
                if (userResponse.getErrorType() == null) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Manejar el caso donde userResponse es nulo
                Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void handleRepositoryError(String errorType) {
        String errorMessage;
        // Asignar mensajes específicos según el tipo de error del repositorio
        switch (errorType) {
            case "password":
                errorMessage = "Contraseña incorrecta";
                break;
            case "unauthorized":
                errorMessage = "Credenciales incorrectas";
                break;
            case "network":
                errorMessage = "Error de red";
                break;
            default:
                errorMessage = "Error desconocido";
                break;
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
    public void goToRegister(View view) {
        // Este método se llama cuando se hace clic en el botón
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
