package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministrativoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Administrativo GetAdministrativo(int idpersona) {
        Administrativo administrativo = new Administrativo();
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
                administrativo.setDni(rs.getString("dni"));
                administrativo.setApellido(rs.getString("apellido"));
                administrativo.setNombres(rs.getString("nombres"));
                administrativo.setSector(rs.getString("sector")); 
                administrativo.setDireccion(rs.getString("direccion"));
                administrativo.setTelefono(rs.getString("telefono"));
                administrativo.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            Logger.getLogger(AdministrativoDAO.class.getName()).log(Level.SEVERE, null, e);   
        }
        return administrativo;
    }
}
