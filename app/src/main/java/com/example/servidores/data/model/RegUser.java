package com.example.servidores.data.model;

import android.text.TextUtils;
import android.util.Patterns;

public class RegUser {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String password_confirmation;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
    //validaciones
    public boolean isValid() {
        return isNombreValid() && isEmailValid() && isPasswordValid() && isConfirmPasswordValid();
    }

    public boolean isNombreValid() {
        return !TextUtils.isEmpty(name);
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid() {
        return !TextUtils.isEmpty(password);
    }

    public boolean isConfirmPasswordValid() {
        return password.equals(password_confirmation);
    }
}
