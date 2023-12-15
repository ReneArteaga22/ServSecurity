package com.example.servidores.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.servidores.data.RegisterRepository;
import com.example.servidores.data.model.RegUser;
import com.example.servidores.data.model.RegisterResponse;

public class RegisterViewModel extends ViewModel {
    private RegisterRepository registerRepository = new RegisterRepository();
    private MutableLiveData<String> registerResult = new MutableLiveData<>();
    private MutableLiveData<RegisterResponse> responseLiveData = new MutableLiveData<>();

    public LiveData<String> registerUser(RegUser reguser) {
        if (reguser.isValid()) {
            registerRepository.registerUser(reguser).observeForever(response -> {
                if (response != null) {
                    // Actualizar LiveData con el resultado de la API
                    registerResult.setValue(response);

                    // Si el registro fue exitoso, también obtener información del usuario
                    if (response.equals("Registro exitoso")) {
                        fetchUserData(reguser.getEmail());
                    }
                } else {
                    registerResult.setValue("Error en el registro: Respuesta nula");
                }
            });
        } else {
            registerResult.setValue("Error en el registro: Datos de registro no válidos");
        }

        return registerResult;
    }

    private void fetchUserData(String email) {
    }

    public LiveData<RegisterResponse> getResponseLiveData() {
        return responseLiveData;
    }
}

