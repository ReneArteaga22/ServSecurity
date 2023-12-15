package com.example.servidores.data.model;

public class RegisterResponse {
    private String message;
    private RegisteredUser user;

    public String getMessage() {
        return message;
    }

    public RegisteredUser getUser() {
        return user;
    }
}
