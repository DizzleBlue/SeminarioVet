
package com.hugodiaz.seminariovet.modelo;

public class Referido {
    private int idreferido;
    private String nombre;
    private String telefono;
    private String email;

    public Referido() {
    }

    public Referido(int idreferido, String nombre, String telefono, String email) {
        this.idreferido = idreferido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdreferido() {
        return idreferido;
    }

    public void setIdreferido(int idreferido) {
        this.idreferido = idreferido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
