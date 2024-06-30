package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilaTurnoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public FilaTurno listar(String fecha) {
        String sql = """
                     select 
                     id,completada
                     from FilaTurno
                     where fecha = ?
                     """;
        
        PuestoFilaTurnoDAO pftdao = new PuestoFilaTurnoDAO();
        //List<FilaTurno> lista = new ArrayList<>();
        FilaTurno ft = new FilaTurno();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1,fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idft = rs.getInt("id");
                ft.setId(idft);
                ft.setFecha(LocalDate.parse(fecha));
                ft.setCompletada(rs.getBoolean("completada"));
                List<PuestoFilaTurno> lista = pftdao.listar(idft);
                lista.forEach((pft) -> ft.add(pft));
                
            }
        } catch (SQLException e) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ft;
    }
    
}
