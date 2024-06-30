package com.hugodiaz.seminariovet.modelo;

abstract public class Animal extends Paciente {
    private int idrazaespecie;
    private String raza;  // est'a linkeado con idrazaespecie
    private String sexo;
    private String fecha_nacimiento;
    private double peso; // en kilos
    private String microchip;
    private String foto;
    private String fecha_ultima_visita;

    public Animal() {
    }

    public Animal(int idrazaespecie,String sexo, String fecha_nacimiento, double peso, String microchip, String foto, String fecha_ultima_visita, int idpaciente, String nombre, int idtutor, int idreferido, String tipo_paciente) {
        super(idpaciente, nombre, idtutor, idreferido, tipo_paciente);
        this.idrazaespecie = idrazaespecie;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.peso = peso;
        this.microchip = microchip;
        this.foto = foto;
        this.fecha_ultima_visita = fecha_ultima_visita;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    public int getIdrazaespecie() {
        return idrazaespecie;
    }
    
    public void setIdrazaespecie(int idrazaespecie) {
        this.idrazaespecie = idrazaespecie;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFecha_ultima_visita() {
        return fecha_ultima_visita;
    }

    public void setFecha_ultima_visita(String fecha_ultima_visita) {
        this.fecha_ultima_visita = fecha_ultima_visita;
    }

}
