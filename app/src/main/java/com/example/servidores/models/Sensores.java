package com.example.servidores.models;

public class Sensores {
    String feed_key;
    String value;
    String tipo;

    public Sensores(String feed_key, String value, String tipo) {
        this.feed_key = feed_key;
        this.value = value;
        this.tipo = tipo;
    }

    public String getFeed_key() {
        return feed_key;
    }

    public void setFeed_key(String feed_key) {
        this.feed_key = feed_key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
