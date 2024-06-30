package com.hugodiaz.seminariovet.modelo;

public abstract class Persona {
    int id;
    String dni;
    String apellido;
    String nombres;
    String direccion;
    String telefono;
    String email;
    Usuario usuario;

    public Persona() {
    }

    public Persona(int id, String dni, String apellido, String nombres, String direccion, String telefono, String email, Usuario usuario) {
        this.id = id;
        this.dni = dni;
        this.apellido = apellido;
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", dni=" + dni + ", apellido=" + apellido + ", nombres=" + nombres + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + ", usuario=" + usuario + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getApellidoNombres(){
        return getApellido() + " " + getNombres();
    }
    
    // para polimorfismo de menu general
    public String getMenuGeneral() {
        return "";
    }
}
