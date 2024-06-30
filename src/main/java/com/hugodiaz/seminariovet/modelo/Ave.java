
package com.hugodiaz.seminariovet.modelo;

public class Ave extends Animal {
    private String plumaje;
    
    public Ave() {
    }

    public Ave(String plumaje, int idrazaespecie, String sexo, String fecha_nacimiento, double peso, String microchip, String foto, String fecha_ultima_visita, int idpaciente, String nombre, int idtutor, int idreferido, String tipo_paciente) {
        super(idrazaespecie, sexo, fecha_nacimiento, peso, microchip, foto, fecha_ultima_visita, idpaciente, nombre, idtutor, idreferido, tipo_paciente);
        this.plumaje = plumaje;
    }

    public String getPlumaje() {
        return plumaje;
    }

    public void setPlumaje(String plumaje) {
        this.plumaje = plumaje;
    }

    
    @Override
    public String getAspecto() {
        return plumaje;
    }
}
