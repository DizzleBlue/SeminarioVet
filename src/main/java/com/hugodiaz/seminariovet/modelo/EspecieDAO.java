package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EspecieDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        String sql = """
                     select 
                     idespecie,nombre
                     from Especie
                     order by nombre
                     """;
        List<Especie> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Especie esp = new Especie();
                esp.setId(rs.getInt("idespecie"));
                esp.setNombre(rs.getString("nombre"));
                lista.add(esp);
            }
        } catch (SQLException e) {
            Logger.getLogger(RazaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    
    public String getNombre(int id) {
        
        String sql = """
                     select 
                     nombre
                     from Especie
                     where idespecie = ?
                     """;
        String ret = "";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                ret = rs.getString("nombre");
            }
        } catch (SQLException e) {
            Logger.getLogger(RazaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ret;
    }
}
