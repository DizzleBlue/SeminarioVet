package com.hugodiaz.seminariovet.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FilaTurno {
    private int id;
    private LocalDate fecha;
    private Queue<PuestoFilaTurno> q;
    private boolean completada;
    
    public FilaTurno() {
        q = new LinkedList<>();
    }

    public void add(PuestoFilaTurno element) {
        q.add(element);
    }
    public PuestoFilaTurno poll() {
        return q.poll();
    }
    
    public void clear() {
        q.clear();
    }
    
    public static int findIndexOfMinimumElement(Queue<PuestoFilaTurno> queue, int index) {
        int min_index = -1;
        int min_element = Integer.MAX_VALUE;

        int size = queue.size();

        for (int i = 0; i < size; i++) {
            PuestoFilaTurno curr_puesto = queue.peek();

            queue.poll();

            int curr_number = curr_puesto.getNumero_asignado();
            
            if (curr_number <= min_element && i <= index) {
                min_index = i;
                min_element = curr_number;
            }

            queue.add(curr_puesto);
        }
        return min_index;
    }
    
    public static void insertElementToRear(Queue<PuestoFilaTurno> queue, int index) {
        
        PuestoFilaTurno min_value = new PuestoFilaTurno();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            PuestoFilaTurno element_at_front = queue.peek();
            queue.poll();
            if (i != index) 
                queue.add(element_at_front);
            else 
                min_value = element_at_front;
        }

        queue.add(min_value);
    }
    
    public void sort() {
        for (int i = 0; i <= this.q.size(); i++) {
            int index = findIndexOfMinimumElement(this.q,this.q.size() - i);
            insertElementToRear(this.q,index);
        }
    }
    public List listarNumeros() {
        List<Integer> lista = new ArrayList<>();
        
        int size = this.q.size();
        for (int i = 0; i < size; i++) {
            PuestoFilaTurno element = this.q.peek();
            this.q.poll();
            int numero = element.getNumero_asignado();
            lista.add(numero);
            this.q.add(element);
        }
        
        return lista;
    }
    public List listarPuestos() {
        List<PuestoFilaTurno> lista = new ArrayList<>();
        int size = this.q.size();
        for (int i = 0; i < size; i++) {
            PuestoFilaTurno element = this.q.peek();
            this.q.poll();
            lista.add(element);
            this.q.add(element);
        }
        return lista;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean getCompletada() {
        return completada;
    }
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
    
    public Queue<PuestoFilaTurno> getQ() {
        return q;
    }

    public void setQ(Queue<PuestoFilaTurno> q) {
        this.q = q;
    }
    
}
