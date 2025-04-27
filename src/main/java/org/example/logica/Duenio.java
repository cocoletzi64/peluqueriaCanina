package org.example.logica;

import jakarta.persistence.*;

@Entity
public class Duenio {
    @Id
    private int id_duenio;
    @Basic
    private String nombre;
    private String cel;

    public Duenio() {
    }

    public Duenio(int id_duenio, String nombre, String cel) {
        this.id_duenio = id_duenio;
        this.nombre = nombre;
        this.cel = cel;
    }

    public int getId_duenio() {
        return id_duenio;
    }

    public void setId_duenio(int id_duenio) {
        this.id_duenio = id_duenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }
}

