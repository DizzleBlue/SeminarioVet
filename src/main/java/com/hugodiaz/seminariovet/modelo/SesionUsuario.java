package com.hugodiaz.seminariovet.modelo;

public class SesionUsuario {
    private int idpersona;
    private Usuario usuario;

    public SesionUsuario() {
    }

    public SesionUsuario(int idpersona, Usuario usuario) {
        this.idpersona = idpersona;
        this.usuario = usuario;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
