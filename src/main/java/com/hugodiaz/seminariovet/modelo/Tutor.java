package com.hugodiaz.seminariovet.modelo;

public class Tutor {
  private int idtutor;
  private String dni;
  private String nombres;
  private String apellido;
  private String direccion;
  private String email;
  private String telefono;
  private String comentario;

    public Tutor() {
    }

    public Tutor(int idtutor, String dni, String nombres, String apellido, String direccion, String email, String telefono, String comentario) {
        this.idtutor = idtutor;
        this.dni = dni;
        this.nombres = nombres;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.comentario = comentario;
    }

    public int getIdtutor() {
        return idtutor;
    }

    public void setIdtutor(int idtutor) {
        this.idtutor = idtutor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getApellidoNombres() {
        return getApellido() + " " + getNombres();
    }

    public String getDniApellidoNombres() {
        return "(" + getDni() + ") " + getApellidoNombres();
    }
    
    @Override
    public String toString() {
        return "Tutor{" + "idtutor=" + idtutor + ", dni=" + dni + ", nombres=" + nombres + ", apellido=" + apellido + ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + ", comentario=" + comentario + '}';
    }
      
}
