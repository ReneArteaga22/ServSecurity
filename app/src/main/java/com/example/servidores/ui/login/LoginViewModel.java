package com.example.servidores.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.example.servidores.data.LoginRepository;
import com.example.servidores.data.model.LoginResponse;
import com.example.servidores.HomeActivity;

public class LoginViewModel extends AndroidViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private LoginRepository loginRepository;
    private MutableLiveData<LoginResponse> loginResponseLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application.getApplicationContext());
    }

    public void loginUser(String email, String password) {
        Log.d(TAG, "loginUser called");
        loginRepository.getUser(email, password, loginResponseLiveData);
    }

    public LiveData<LoginResponse> getUserResponseLiveData() {
        return loginResponseLiveData;
    }

    public void handleLoginResponse(LoginResponse loginResponse) {
        if (loginResponse != null && loginResponse.getAccess_token() != null) {
            // Token exitoso, redirigir al HomeActivity
            redirectToHomeActivity();
        } else {
            // Error en la respuesta o token nulo, mostrar mensaje de error
            // Puedes manejar el mensaje de error de acuerdo a tu lógica
            // Por ahora, simplemente imprimimos un log
            Log.e(TAG, "Error en la respuesta del servidor o token nulo");
        }
    }

    private void redirectToHomeActivity() {
        Intent intent = new Intent(getApplication(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getApplication().startActivity(intent);
    }
}
