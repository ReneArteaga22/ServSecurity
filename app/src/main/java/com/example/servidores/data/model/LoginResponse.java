package com.example.servidores.data.model;

public class LoginResponse  {


        private String access_token;
        private String token_type;
        private long expires_in;
        private User user;



    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    private String errorType;

    public LoginResponse() {

    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setValue(LoginResponse loginResponse) {
    }


    public class User {
        private int id;
        private String name;
        private String lastname;
        private String email;
        private String email_verified_at; // Puede ser null dependiendo de la API
        private int tipo_usuario;
        private Boolean is_active; // Puede ser null dependiendo de la API

        public User(int id, String name, String lastname, String email, String email_verified_at, int tipo_usuario, Boolean is_active) {
            this.id = id;
            this.name = name;
            this.lastname = lastname;
            this.email = email;
            this.email_verified_at = email_verified_at;
            this.tipo_usuario = tipo_usuario;
            this.is_active = is_active;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public int getTipo_usuario() {
            return tipo_usuario;
        }

        public void setTipo_usuario(int tipo_usuario) {
            this.tipo_usuario = tipo_usuario;
        }

        public Boolean getIs_active() {
            return is_active;
        }

        public void setIs_active(Boolean is_active) {
            this.is_active = is_active;
        }
    }


}
