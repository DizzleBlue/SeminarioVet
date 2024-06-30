package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaninoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public int agregar(Canino can){
        String sql = """
            insert into Paciente(idtutor,nombre,idrazaespecie,sexo,fecha_nacimiento,peso,
                     microchip,idreferido,pelaje,tipo_paciente)
            values(?,?,?,?,?,?,?,?,?,?)
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, can.getIdtutor());
            ps.setString(2, can.getNombre());
            ps.setInt(3, can.getIdrazaespecie());
            ps.setString(4, can.getSexo());
            ps.setString(5, can.getFecha_nacimiento());
            ps.setDouble(6, can.getPeso());
            ps.setString(7, can.getMicrochip());
            ps.setInt(8, can.getIdreferido());
            ps.setString(9,can.getPelaje());
            ps.setString(10, "Canino");
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    public int actualizar(Canino can){
        String sql = """
            update Paciente
                set idtutor=?,nombre=?,idrazaespecie=?,sexo=?,fecha_nacimiento=?,peso=?,
                    microchip=?,idreferido=?,fecha_ultima_visita=?,pelaje=?,tipo_paciente=?
            where idpaciente=?
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, can.getIdtutor());
            ps.setString(2, can.getNombre());
            ps.setInt(3, can.getIdrazaespecie());
            ps.setString(4, can.getSexo());
            ps.setString(5, can.getFecha_nacimiento());
            ps.setDouble(6, can.getPeso());
            ps.setString(7, can.getMicrochip());
            ps.setInt(8, can.getIdreferido());
            ps.setString(9,can.getFecha_ultima_visita());
            ps.setString(10,can.getPelaje());
            ps.setString(11, "Canino");
            ps.setInt(12, can.getIdpaciente());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    public Canino listarUno(int idpaciente) {
        Canino can = new Canino();
        String sql = """
                     select 
                     idtutor,nombre,idrazaespecie,sexo,fecha_nacimiento,
                     peso,microchip,foto,idreferido,fecha_ultima_visita,pelaje
                     from Paciente
                     where idpaciente = ?
                     """;
        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                can.setIdtutor(rs.getInt("idtutor"));
                can.setNombre(rs.getString("nombre"));
                can.setIdrazaespecie(rs.getInt("idrazaespecie"));
                can.setSexo(rs.getString("sexo"));
                can.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                can.setPeso(rs.getDouble("peso"));
                can.setMicrochip(rs.getString("microchip"));
                can.setFoto(rs.getString("foto"));
                can.setIdreferido(rs.getInt("idreferido"));
                can.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                can.setPelaje(rs.getString("pelaje"));
            }
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return can;

    }
    
}
