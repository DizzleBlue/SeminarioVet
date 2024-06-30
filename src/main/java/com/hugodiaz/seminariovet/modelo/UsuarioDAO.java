package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public SesionUsuario validar(String user, String passw) {
        Usuario usu = new Usuario();
        Rol rol = new Rol();
        SesionUsuario ses = new SesionUsuario();
        int idpersona = 0;
        
        String sql = """
            select 
                idusuario,c.nombre as rol,menu_gral,e.idemp 
            from 
                Usuario a inner join Entidad b 
                on a.id_entidad=b.idet 
                inner join Rol c 
                on b.idrol=c.idrol 
                inner join Empleado e
                on e.idemp = b.idemp 
                where login=?
                and passw=?
                and estado='A';
                """;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, passw);
            rs = ps.executeQuery();
            while (rs.next()) {
                usu.setId(rs.getInt("idusuario"));
                rol.setName(rs.getString("rol"));
                rol.setMenu_gral(rs.getString("menu_gral"));
                idpersona = rs.getInt("idemp");
                
                //completo el objeto usuario
                usu.setLogin(user);
                usu.setPassw(passw);
                usu.setEstado("A");
            }
            usu.setRol(rol);
            ses.setIdpersona(idpersona);
            ses.setUsuario(usu);
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ses;
    }
}
