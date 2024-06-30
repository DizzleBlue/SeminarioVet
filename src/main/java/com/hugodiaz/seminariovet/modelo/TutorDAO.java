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

public class TutorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Operaciones CRUD
    public List listar() {
        String sql = """
                     select 
                     idtutor,dni,nombres,apellido,
                     direccion,email,telefono,comentario
                     from Tutor
                     order by apellido,nombres
                     """;
        List<Tutor> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tutor tut = new Tutor();
                tut.setIdtutor(rs.getInt("idtutor"));
                tut.setDni(rs.getString("dni"));
                tut.setNombres(rs.getString("nombres"));
                tut.setApellido(rs.getString("apellido"));
                tut.setDireccion(rs.getString("direccion"));
                tut.setEmail(rs.getString("email"));
                tut.setTelefono(rs.getString("telefono"));
                tut.setComentario(rs.getString("comentario"));
                lista.add(tut);
            }
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
    public Tutor listar(int idt) {
        Tutor tut = new Tutor();
        String sql = """
                     select 
                     idtutor,dni,nombres,apellido,
                     direccion,email,telefono,comentario
                     from Tutor
                     where idtutor = ?
                     """;
        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idt);
            rs = ps.executeQuery();
            while (rs.next()) {
                tut.setIdtutor(rs.getInt("idtutor"));
                tut.setDni(rs.getString("dni"));
                tut.setNombres(rs.getString("nombres"));
                tut.setApellido(rs.getString("apellido"));
                tut.setDireccion(rs.getString("direccion"));
                tut.setEmail(rs.getString("email"));
                tut.setTelefono(rs.getString("telefono"));
                tut.setComentario(rs.getString("comentario"));
        
            }
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return tut;
    }
    public int agregar(Tutor tut){
        String sql = """
            insert into Tutor(dni,nombres,apellido,direccion,email,telefono,comentario)
            values(?,?,?,?,?,?,?)
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, tut.getDni());
            ps.setString(2, tut.getNombres());
            ps.setString(3, tut.getApellido());
            ps.setString(4, tut.getDireccion());
            ps.setString(5, tut.getEmail());
            ps.setString(6, tut.getTelefono());
            ps.setString(7, tut.getComentario());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    public int actualizar(Tutor tut) {
        String sql = """
            update Tutor set dni=?,nombres=?,apellido=?,direccion=?,email=?,telefono=?,comentario=?
            where idtutor = ?
                     """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1,tut.getDni());
            ps.setString(2, tut.getNombres());
            ps.setString(3, tut.getApellido());
            ps.setString(4, tut.getDireccion());
            ps.setString(5, tut.getEmail());
            ps.setString(6, tut.getTelefono());
            ps.setString(7, tut.getComentario());
            ps.setInt(8, tut.getIdtutor());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    public void delete(int idt) {
        String sql = "delete from Tutor where idtutor = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idt);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
