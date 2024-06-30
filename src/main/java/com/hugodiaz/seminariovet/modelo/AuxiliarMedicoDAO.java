package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuxiliarMedicoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public AuxiliarMedico GetAuxiliarMedico(int idpersona) {
        AuxiliarMedico auxiliarmedico = new AuxiliarMedico();
    
        String sql = """
            select 
                dni,apellido,nombres,matricula,direccion,telefono,email 
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
                auxiliarmedico.setDni(rs.getString("dni"));
                auxiliarmedico.setApellido(rs.getString("apellido"));
                auxiliarmedico.setNombres(rs.getString("nombres"));
                auxiliarmedico.setMatricula(rs.getString("matricula"));
                auxiliarmedico.setDireccion(rs.getString("direccion"));
                auxiliarmedico.setTelefono(rs.getString("telefono"));
                auxiliarmedico.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            Logger.getLogger(AuxiliarMedicoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return auxiliarmedico;
    }
    
}
