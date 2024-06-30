package com.hugodiaz.seminariovet.util;

import java.util.HashMap;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class Util {
    
    private Util(){}
    
    public static String construyeMensaje(String msg, String type) {
        String ret;
        
        ret = "<div class=\"alert alert-" + type + " alert-dismissible\" role=\"alert\">";
        ret += "<div>" + msg + "</div>";
        ret += "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>";
        ret += "</div>";
        return ret;
    }
    
    
    public static HashMap<Integer, String> getEspecies() { //IMPORTANTE!: cada vez que se crea una nueva especie
                                                           //(nueva clase), agregar tambien aca!
                                                           //e insertar en la tabla Especie
        HashMap<Integer, String> especies = new HashMap<>();
        especies.put(3,"Ave");
        especies.put(1,"Canino");
        especies.put(2,"Felino");
        return especies;
    }
    
    public static String getFechaHoy() {
        ZoneId zonedId = ZoneId.of("America/Argentina/Buenos_Aires");
        LocalDate hoy = LocalDate.now(zonedId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String shoy = hoy.format(formatter);
        return shoy;
    }
}
