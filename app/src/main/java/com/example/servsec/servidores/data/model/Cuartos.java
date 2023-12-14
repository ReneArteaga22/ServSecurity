package com.example.servsec.servidores.data.model;


public class Cuartos {
    private String id;
    private String nombre;
    private String propietario;

    public Cuartos(String id, String nombre, String propietario) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
