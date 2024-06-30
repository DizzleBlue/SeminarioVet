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

public class PacienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Operaciones CRUD
    public List listar() {
        String sql = """
                     select 
                     idpaciente,p.idtutor,p.nombre,p.idrazaespecie,r.nombre as raza,
                     sexo,fecha_nacimiento,peso,microchip,foto,idreferido,
                     fecha_ultima_visita,pelaje,plumaje,tipo_paciente,
                     t.nombres as nombre_tutor, t.apellido as apellido_tutor
                     from Paciente p
                     left join Tutor t
                     on p.idtutor = t.idtutor
                     left join RazaEspecie r
                     on p.idrazaespecie = r.idrazaespecie
                     order by idtutor,p.nombre
                     """;
        List<Paciente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nt = rs.getString("apellido_tutor") + " " + rs.getString("nombre_tutor");
                String tp = rs.getString("tipo_paciente");
                
                switch (tp) {
                    case "Canino" -> {
                        Canino can = new Canino();
                        can.setIdpaciente(rs.getInt("idpaciente"));
                        can.setNombre(rs.getString("nombre"));
                        can.setIdtutor(rs.getInt("idtutor"));
                        can.setNombreTutor(nt);
                        can.setIdreferido(rs.getInt("idreferido"));
                        can.setTipoPaciente(tp);
                        can.setSexo(rs.getString("sexo"));
                        can.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        can.setPeso(rs.getDouble("peso"));
                        can.setMicrochip(rs.getString("microchip"));
                        can.setFoto(rs.getString("foto"));
                        can.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        can.setPelaje(rs.getString("pelaje"));
                        can.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        can.setRaza(rs.getString("raza"));
                        lista.add(can);
                    }
                    case "Felino" -> {
                        Felino fel = new Felino();
                        fel.setIdpaciente(rs.getInt("idpaciente"));
                        fel.setNombre(rs.getString("nombre"));
                        fel.setIdtutor(rs.getInt("idtutor"));
                        fel.setNombreTutor(nt);
                        fel.setIdreferido(rs.getInt("idreferido"));
                        fel.setTipoPaciente(tp);
                        fel.setSexo(rs.getString("sexo"));
                        fel.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        fel.setPeso(rs.getDouble("peso"));
                        fel.setMicrochip(rs.getString("microchip"));
                        fel.setFoto(rs.getString("foto"));
                        fel.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        fel.setPelaje(rs.getString("pelaje"));
                        fel.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        fel.setRaza(rs.getString("raza"));
                        lista.add(fel);
                    }
                    case "Ave" -> {
                        Ave ave = new Ave();
                        ave.setIdpaciente(rs.getInt("idpaciente"));
                        ave.setNombre(rs.getString("nombre"));
                        ave.setIdtutor(rs.getInt("idtutor"));
                        ave.setNombreTutor(nt);
                        ave.setIdreferido(rs.getInt("idreferido"));
                        ave.setTipoPaciente(tp);
                        ave.setSexo(rs.getString("sexo"));
                        ave.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        ave.setPeso(rs.getDouble("peso"));
                        ave.setMicrochip(rs.getString("microchip"));
                        ave.setFoto(rs.getString("foto"));
                        ave.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        ave.setPlumaje(rs.getString("plumaje"));
                        ave.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        ave.setRaza(rs.getString("raza"));
                        lista.add(ave);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    
    public List listar(int idtutor) {
        String sql = """
                     select 
                     idpaciente,idtutor,p.nombre,p.idrazaespecie,r.nombre as raza,
                     sexo,fecha_nacimiento,peso,microchip,foto,idreferido,
                     fecha_ultima_visita,pelaje,plumaje,tipo_paciente
                     from Paciente p
                     left join RazaEspecie r
                     on p.idrazaespecie = r.idrazaespecie
                     where idtutor = ?
                     order by p.nombre
                     """;
        List<Paciente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idtutor);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tp = rs.getString("tipo_paciente");
                
                switch (tp) {
                    case "Canino" -> {
                        Canino can = new Canino();
                        can.setIdpaciente(rs.getInt("idpaciente"));
                        can.setNombre(rs.getString("nombre"));
                        can.setIdtutor(rs.getInt("idtutor"));
                        can.setIdreferido(rs.getInt("idreferido"));
                        can.setTipoPaciente(tp);
                        can.setSexo(rs.getString("sexo"));
                        can.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        can.setPeso(rs.getDouble("peso"));
                        can.setMicrochip(rs.getString("microchip"));
                        can.setFoto(rs.getString("foto"));
                        can.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        can.setPelaje(rs.getString("pelaje"));
                        can.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        can.setRaza(rs.getString("raza"));
                        lista.add(can);
                    }
                    case "Felino" -> {
                        Felino fel = new Felino();
                        fel.setIdpaciente(rs.getInt("idpaciente"));
                        fel.setNombre(rs.getString("nombre"));
                        fel.setIdtutor(rs.getInt("idtutor"));
                        fel.setIdreferido(rs.getInt("idreferido"));
                        fel.setTipoPaciente(tp);
                        fel.setSexo(rs.getString("sexo"));
                        fel.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        fel.setPeso(rs.getDouble("peso"));
                        fel.setMicrochip(rs.getString("microchip"));
                        fel.setFoto(rs.getString("foto"));
                        fel.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        fel.setPelaje(rs.getString("pelaje"));
                        fel.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        fel.setRaza(rs.getString("raza"));
                        lista.add(fel);
                    }
                    case "Ave" -> {
                        Ave ave = new Ave();
                        ave.setIdpaciente(rs.getInt("idpaciente"));
                        ave.setNombre(rs.getString("nombre"));
                        ave.setIdtutor(rs.getInt("idtutor"));
                        ave.setIdreferido(rs.getInt("idreferido"));
                        ave.setTipoPaciente(tp);
                        ave.setSexo(rs.getString("sexo"));
                        ave.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        ave.setPeso(rs.getDouble("peso"));
                        ave.setMicrochip(rs.getString("microchip"));
                        ave.setFoto(rs.getString("foto"));
                        ave.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        ave.setPlumaje(rs.getString("plumaje"));
                        ave.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        ave.setRaza(rs.getString("raza"));
                        lista.add(ave);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }    
    public List getListUnPaciente(int idpaciente) {
        String sql = """
                     select 
                     idpaciente,idtutor,p.nombre,p.idrazaespecie,r.nombre as raza,
                     sexo,fecha_nacimiento,peso,microchip,foto,idreferido,
                     fecha_ultima_visita,pelaje,plumaje,tipo_paciente
                     from Paciente p
                     left join RazaEspecie r
                     on p.idrazaespecie = r.idrazaespecie
                     where idpaciente = ?
                     """;
        List<Paciente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tp = rs.getString("tipo_paciente");
                
                switch (tp) {
                    case "Canino" -> {
                        Canino can = new Canino();
                        can.setIdpaciente(rs.getInt("idpaciente"));
                        can.setNombre(rs.getString("nombre"));
                        can.setIdtutor(rs.getInt("idtutor"));
                        can.setIdreferido(rs.getInt("idreferido"));
                        can.setTipoPaciente(tp);
                        can.setSexo(rs.getString("sexo"));
                        can.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        can.setPeso(rs.getDouble("peso"));
                        can.setMicrochip(rs.getString("microchip"));
                        can.setFoto(rs.getString("foto"));
                        can.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        can.setPelaje(rs.getString("pelaje"));
                        can.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        can.setRaza(rs.getString("raza"));
                        lista.add(can);
                    }
                    case "Felino" -> {
                        Felino fel = new Felino();
                        fel.setIdpaciente(rs.getInt("idpaciente"));
                        fel.setNombre(rs.getString("nombre"));
                        fel.setIdtutor(rs.getInt("idtutor"));
                        fel.setIdreferido(rs.getInt("idreferido"));
                        fel.setTipoPaciente(tp);
                        fel.setSexo(rs.getString("sexo"));
                        fel.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        fel.setPeso(rs.getDouble("peso"));
                        fel.setMicrochip(rs.getString("microchip"));
                        fel.setFoto(rs.getString("foto"));
                        fel.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        fel.setPelaje(rs.getString("pelaje"));
                        fel.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        fel.setRaza(rs.getString("raza"));
                        lista.add(fel);
                    }
                    case "Ave" -> {
                        Ave ave = new Ave();
                        ave.setIdpaciente(rs.getInt("idpaciente"));
                        ave.setNombre(rs.getString("nombre"));
                        ave.setIdtutor(rs.getInt("idtutor"));
                        ave.setIdreferido(rs.getInt("idreferido"));
                        ave.setTipoPaciente(tp);
                        ave.setSexo(rs.getString("sexo"));
                        ave.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                        ave.setPeso(rs.getDouble("peso"));
                        ave.setMicrochip(rs.getString("microchip"));
                        ave.setFoto(rs.getString("foto"));
                        ave.setFecha_ultima_visita(rs.getString("fecha_ultima_visita"));
                        ave.setPlumaje(rs.getString("plumaje"));
                        ave.setIdrazaespecie(rs.getInt("idrazaespecie"));
                        ave.setRaza(rs.getString("raza"));
                        lista.add(ave);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return lista;
    }
    public int getEspeciePaciente(int idpaciente) {
        String sql = """
                     select 
                     idespecie
                     from Paciente p
                     inner join Especie r
                     on p.tipo_paciente = r.nombre
                     where idpaciente = ?
                     """;
        int ret = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            rs = ps.executeQuery();
            if (rs.next()) {
                ret =rs.getInt("idespecie");
            }
        } catch (SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return ret;
    }    
    public String getNombreEspeciePaciente(int idpaciente) {
        String sql = """
                     select 
                     r.nombre
                     from Paciente p
                     inner join Especie r
                     on p.tipo_paciente = r.nombre
                     where idpaciente = ?
                     """;
        String ret = "";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            rs = ps.executeQuery();
            if (rs.next()) {
                ret =rs.getString("nombre");
            }
        } catch (SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return ret;
    }    
    
    public List getListUnPacienteBlank(String tipo_paciente) {
        List<Paciente> lista = new ArrayList<>();
        switch (tipo_paciente) {
            case "Canino" -> {
                Canino can = new Canino();
                lista.add(can);
            }
            case "Felino" -> {
                Felino fel = new Felino();
                lista.add(fel);
            }
            case "Ave" -> {
                Ave ave = new Ave();
                lista.add(ave);
            }
        }
        return lista;
    } 
    public void delete(int idp) {
        String sql = "delete from Paciente where idpaciente = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idp);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
