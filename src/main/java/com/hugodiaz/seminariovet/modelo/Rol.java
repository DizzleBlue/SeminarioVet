package com.hugodiaz.seminariovet.modelo;

public class Rol {
    private int id;
    private String name;
    private String menu_gral;

    public Rol() {
    }

    public Rol(int id, String name, String menu_gral) {
        this.id = id;
        this.name = name;
        this.menu_gral = menu_gral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu_gral() {
        return menu_gral;
    }

    public void setMenu_gral(String menu_gral) {
        this.menu_gral = menu_gral;
    }
    
    
}
