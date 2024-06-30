package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AveDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    
    public int agregar(Ave ave){
        String sql = """
            insert into Paciente(idtutor,nombre,idrazaespecie,sexo,fecha_nacimiento,peso,
                     microchip,idreferido,plumaje,tipo_paciente)
            values(?,?,?,?,?,?,?,?,?,?)
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ave.getIdtutor());
            ps.setString(2, ave.getNombre());
            ps.setInt(3, ave.getIdrazaespecie());
            ps.setString(4, ave.getSexo());
            ps.setString(5, ave.getFecha_nacimiento());
            ps.setDouble(6, ave.getPeso());
            ps.setString(7, ave.getMicrochip());
            ps.setInt(8, ave.getIdreferido());
            ps.setString(9,ave.getPlumaje());
            ps.setString(10, "Ave");
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    public int actualizar(Ave ave){
        String sql = """
            update Paciente
                set idtutor=?,nombre=?,idrazaespecie=?,sexo=?,fecha_nacimiento=?,peso=?,
                    microchip=?,idreferido=?,fecha_ultima_visita=?,plumaje=?,tipo_paciente=?
            where idpaciente=?
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ave.getIdtutor());
            ps.setString(2, ave.getNombre());
            ps.setInt(3, ave.getIdrazaespecie());
            ps.setString(4, ave.getSexo());
            ps.setString(5, ave.getFecha_nacimiento());
            ps.setDouble(6, ave.getPeso());
            ps.setString(7, ave.getMicrochip());
            ps.setInt(8, ave.getIdreferido());
            ps.setString(9,ave.getFecha_ultima_visita());
            ps.setString(10,ave.getPlumaje());
            ps.setString(11, "Ave");
            ps.setInt(12, ave.getIdpaciente());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    
}
