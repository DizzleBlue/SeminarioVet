package com.hugodiaz.seminariovet.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class PuestoFilaTurno {
    private int id;
    private LocalDateTime fechahora_ingreso;
    private Tutor tutor;
    private List<Paciente> listaUnPaciente;  // lista con referencia a un paciente por el polimorfismo
    private int numero_asignado;
    private LocalDateTime fechahora_egreso;
    private boolean cancelado;
    private boolean atendido;

    public PuestoFilaTurno() {
    }

    public PuestoFilaTurno(int id, LocalDateTime fechahora_ingreso, Tutor tutor, List<Paciente> listaUnPaciente, int numero_asignado, LocalDateTime fechahora_egreso, boolean cancelado, boolean atendido) {
        this.id = id;
        this.fechahora_ingreso = fechahora_ingreso;
        this.tutor = tutor;
        this.listaUnPaciente = listaUnPaciente;
        this.numero_asignado = numero_asignado;
        this.fechahora_egreso = fechahora_egreso;
        this.cancelado = cancelado;
        this.atendido = atendido;
    }

    public PuestoFilaTurno(int numero_asignado) {
        this.numero_asignado = numero_asignado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechahora_ingreso() {
        return fechahora_ingreso;
    }

    public void setFechahora_ingreso(LocalDateTime fechahora_ingreso) {
        this.fechahora_ingreso = fechahora_ingreso;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<Paciente> getListaUnPaciente() {
        return listaUnPaciente;
    }

    public void setListaUnPaciente(List<Paciente> listaUnPaciente) {
        this.listaUnPaciente = listaUnPaciente;
    }

    public int getNumero_asignado() {
        return numero_asignado;
    }

    public void setNumero_asignado(int numero_asignado) {
        this.numero_asignado = numero_asignado;
    }

    public LocalDateTime getFechahora_egreso() {
        return fechahora_egreso;
    }

    public void setFechahora_egreso(LocalDateTime fechahora_egreso) {
        this.fechahora_egreso = fechahora_egreso;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
    

    
}
