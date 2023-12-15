package com.example.servidores.CrearCuarto;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.servidores.data.RegisterRepository;
import com.example.servidores.data.model.CrearCuartoUser;
import com.example.servidores.data.model.RegUser;
import com.example.servidores.data.model.RegisterResponse;

public class CrearCuartoViewModel extends ViewModel {
    private CrearCuartoRepository crearCuartoRepository = new CrearCuartoRepository();

    public LiveData<String> crearCuarto(Context context, CrearCuartoUser crearCuartoUser) {
        MutableLiveData<String> crearCuartoResult = new MutableLiveData<>();

        if (crearCuartoUser.isValid()) {
            crearCuartoRepository.crearCuarto(context, crearCuartoUser).observe((LifecycleOwner) context, response -> {
                if (response != null) {

                    crearCuartoResult.setValue(response);


                    if (response.equals("Registro exitoso")) {
                        fetchUserData(crearCuartoUser.getNombre());
                    }
                } else {
                    crearCuartoResult.setValue("Error en el registro: Respuesta nula");
                }
            });
        } else {
            crearCuartoResult.setValue("Error en el registro: Datos de registro no válidos");
        }

        return crearCuartoResult;
    }

    private void fetchUserData(String nombre) {

    }
}
