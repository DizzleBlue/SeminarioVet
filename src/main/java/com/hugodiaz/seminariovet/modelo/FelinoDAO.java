package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FelinoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    
    public int agregar(Felino fel){
        String sql = """
            insert into Paciente(idtutor,nombre,idrazaespecie,sexo,fecha_nacimiento,peso,
                     microchip,idreferido,pelaje,tipo_paciente)
            values(?,?,?,?,?,?,?,?,?,?)
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fel.getIdtutor());
            ps.setString(2, fel.getNombre());
            ps.setInt(3, fel.getIdrazaespecie());
            ps.setString(4, fel.getSexo());
            ps.setString(5, fel.getFecha_nacimiento());
            ps.setDouble(6, fel.getPeso());
            ps.setString(7, fel.getMicrochip());
            ps.setInt(8, fel.getIdreferido());
            ps.setString(9,fel.getPelaje());
            ps.setString(10, "Felino");
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    public int actualizar(Felino fel){
        String sql = """
            update Paciente
                set idtutor=?,nombre=?,idrazaespecie=?,sexo=?,fecha_nacimiento=?,peso=?,
                    microchip=?,idreferido=?,fecha_ultima_visita=?,pelaje=?,tipo_paciente=?
            where idpaciente=?
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fel.getIdtutor());
            ps.setString(2, fel.getNombre());
            ps.setInt(3, fel.getIdrazaespecie());
            ps.setString(4, fel.getSexo());
            ps.setString(5, fel.getFecha_nacimiento());
            ps.setDouble(6, fel.getPeso());
            ps.setString(7, fel.getMicrochip());
            ps.setInt(8, fel.getIdreferido());
            ps.setString(9,fel.getFecha_ultima_visita());
            ps.setString(10,fel.getPelaje());
            ps.setString(11, "Felino");
            ps.setInt(12, fel.getIdpaciente());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    
}
