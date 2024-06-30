package com.hugodiaz.seminariovet.modelo;

public class Canino extends Animal {
    private String pelaje;
    
    public Canino() {
    }

    public Canino(String pelaje, int idrazaespecie, String sexo, String fecha_nacimiento, double peso, String microchip, String foto, String fecha_ultima_visita, int idpaciente, String nombre, int idtutor, int idreferido, String tipo_paciente) {
        super(idrazaespecie, sexo, fecha_nacimiento, peso, microchip, foto, fecha_ultima_visita, idpaciente, nombre, idtutor, idreferido, tipo_paciente);
        this.pelaje = pelaje;
    }

    public String getPelaje() {
        return pelaje;
    }

    public void setPelaje(String pelaje) {
        this.pelaje = pelaje;
    }
    

    @Override
    public String getAspecto() {
        return pelaje;
    }

}
