package com.example.servidores.data.model;

import android.text.TextUtils;

public class CrearCuartoUser {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public boolean isValid() {
        return isNombreValid();
    }

    public boolean isNombreValid() {
        return !TextUtils.isEmpty(nombre);
    }

}
