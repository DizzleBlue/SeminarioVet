package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectivoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Directivo GetDirectivo(int idpersona) {
        Directivo directivo = new Directivo();
        String sql = """
            select 
                dni,apellido,nombres,sector,direccion,telefono,email 
            from 
                Empleado
            where
                idemp = ?
            """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpersona);
            rs = ps.executeQuery();
            while (rs.next()) {
                directivo.setDni(rs.getString("dni"));
                directivo.setApellido(rs.getString("apellido"));
                directivo.setNombres(rs.getString("nombres"));
                directivo.setSector(rs.getString("sector")); 
                directivo.setDireccion(rs.getString("direccion"));
                directivo.setTelefono(rs.getString("telefono"));
                directivo.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            Logger.getLogger(DirectivoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return directivo;
    }
    
}
