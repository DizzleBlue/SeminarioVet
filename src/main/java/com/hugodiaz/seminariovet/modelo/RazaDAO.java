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

public class RazaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        String sql = """
                     select 
                     idrazaespecie,idespecie,nombre
                     from RazaEspecie
                     order by idespecie,nombre
                     """;
        List<Raza> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Raza raz = new Raza(rs.getInt("idrazaespecie"),rs.getInt("idespecie"),rs.getString("nombre"));
                lista.add(raz);
            }
        } catch (SQLException e) {
            Logger.getLogger(RazaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public List listar(int idespecie) {
        String sql = """
                     select 
                     idrazaespecie,nombre
                     from RazaEspecie
                     where idespecie = ?
                     order by nombre
                     """;
        List<Raza> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecie);
            rs = ps.executeQuery();
            while (rs.next()) {
                Raza raz = new Raza();
                raz.setIdraza(rs.getInt("idrazaespecie"));
                raz.setIdespecie(idespecie);
                raz.setNombre(rs.getString("nombre"));
                lista.add(raz);
            }
        } catch (SQLException e) {
            Logger.getLogger(RazaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    
}
