package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Medico GetMedico(int idpersona) {
        Medico medico = new Medico();
        String sql = """
            select 
                dni,apellido,nombres,matricula,especialidad,direccion,telefono,email 
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
                medico.setDni(rs.getString("dni"));
                medico.setApellido(rs.getString("apellido"));
                medico.setNombres(rs.getString("nombres"));
                medico.setMatricula(rs.getString("matricula"));
                medico.setEspecialidad(rs.getString("especialidad"));
                medico.setDireccion(rs.getString("direccion"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return medico;
    }
}
