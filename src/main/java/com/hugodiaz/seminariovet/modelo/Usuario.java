package com.hugodiaz.seminariovet.modelo;

public class Usuario {
    private int id;
    private String login;
    private String passw;
    private String estado;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int id, String login, String passw, String estado, Rol rol) {
        this.id = id;
        this.login = login;
        this.passw = passw;
        this.estado = estado;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
}
