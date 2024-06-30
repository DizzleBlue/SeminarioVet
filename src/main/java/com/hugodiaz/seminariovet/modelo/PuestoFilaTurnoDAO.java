package com.hugodiaz.seminariovet.modelo;

import com.hugodiaz.seminariovet.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PuestoFilaTurnoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(int idfilaturno) {
        String sql = """
                     select 
                     id,fecha_ingreso,idtutor,idpaciente,numero_asignado,
                     fecha_egreso,cancelado,atendido
                     from PuestoFilaTurno
                     where idfilaturno = ?
                     """;
        List<PuestoFilaTurno> lista = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");  // formato fechahora en tabla es: 2024-06-29 13:00
        String s;
        Matcher matcher;
        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idfilaturno);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                PuestoFilaTurno pft = new PuestoFilaTurno();
                pft.setId(rs.getInt("id"));
                
                s = rs.getString("fecha_ingreso");
                if (s != null) {
                    matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        pft.setFechahora_ingreso(LocalDateTime.parse(s,formatter));
                    }
                }
                
                int idt = rs.getInt("idtutor");
                TutorDAO tutdao = new TutorDAO();
                Tutor tut = tutdao.listar(idt);
                pft.setTutor(tut);
                PacienteDAO pacdao = new PacienteDAO();
                int idp = rs.getInt("idpaciente");
                List listapaciente = pacdao.getListUnPaciente(idp);
                pft.setListaUnPaciente(listapaciente);
                pft.setNumero_asignado(rs.getInt("numero_asignado"));
                
                s = rs.getString("fecha_egreso");
                if (s != null) {
                    matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        pft.setFechahora_egreso(LocalDateTime.parse(s,formatter));
                    }
                }
                pft.setCancelado(rs.getBoolean("cancelado"));
                pft.setAtendido(rs.getBoolean("atendido"));
                lista.add(pft);
            }
        } catch (SQLException | DateTimeParseException | NullPointerException e) {
            Logger.getLogger(PuestoFilaTurnoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

}
