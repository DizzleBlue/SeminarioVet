package com.hugodiaz.seminariovet.modelo;

public class Raza {
    private int idraza;
    private int idespecie;
    private String nombre;

    public Raza() {
    }

    public Raza(int idraza, int idespecie, String nombre) {
        this.idraza = idraza;
        this.idespecie = idespecie;
        this.nombre = nombre;
    }

    public int getIdraza() {
        return idraza;
    }

    public void setIdraza(int idraza) {
        this.idraza = idraza;
    }

    public int getIdespecie() {
        return idespecie;
    }

    public void setIdespecie(int idespecie) {
        this.idespecie = idespecie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
