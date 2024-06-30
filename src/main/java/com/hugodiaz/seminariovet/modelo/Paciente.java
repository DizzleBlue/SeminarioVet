package com.hugodiaz.seminariovet.modelo;

abstract public class Paciente {
    private int idpaciente;
    private String nombre;
    private int idtutor;
    private String nombreTutor;
    private int idreferido;
    
    // discriminator column
    private String tipo_paciente;
    
    public Paciente() {
    }

    public Paciente(int idpaciente, String nombre, int idtutor, int idreferido, String tipo_paciente) {
        this.idpaciente = idpaciente;
        this.nombre = nombre;
        this.idtutor = idtutor;
        this.idreferido = idreferido;
        this.tipo_paciente = tipo_paciente;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdtutor() {
        return idtutor;
    }

    public void setIdtutor(int idtutor) {
        this.idtutor = idtutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }
    
    public int getIdreferido() {
        return idreferido;
    }

    public void setIdreferido(int idreferido) {
        this.idreferido = idreferido;
    }
    
    abstract public String getAspecto();   // el aspecto externo (color, plumaje, etc)

    public String getTipoPaciente() {
        return tipo_paciente;
    }

    public void setTipoPaciente(String tipo_paciente) {
        this.tipo_paciente = tipo_paciente;
    }
    
}
