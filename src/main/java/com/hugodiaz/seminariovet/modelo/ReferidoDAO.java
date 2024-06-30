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

public class ReferidoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        String sql = """
                     select 
                     idreferido,nombre,telefono,email
                     from Referido
                     order by nombre
                     """;
        List<Referido> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Referido ref = new Referido();
                ref.setIdreferido(rs.getInt("idreferido"));
                ref.setNombre(rs.getString("nombre"));
                ref.setTelefono(rs.getString("telefono"));
                ref.setEmail(rs.getString("email"));
                lista.add(ref);
            }
        } catch (SQLException e) {
            Logger.getLogger(RazaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
        
    }
    
}
