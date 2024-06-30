
package com.hugodiaz.seminariovet.controlador;

import com.hugodiaz.seminariovet.modelo.Administrativo;
import com.hugodiaz.seminariovet.modelo.AdministrativoDAO;
import com.hugodiaz.seminariovet.modelo.AuxiliarMedico;
import com.hugodiaz.seminariovet.modelo.AuxiliarMedicoDAO;
import com.hugodiaz.seminariovet.modelo.Directivo;
import com.hugodiaz.seminariovet.modelo.DirectivoDAO;
import com.hugodiaz.seminariovet.modelo.Medico;
import com.hugodiaz.seminariovet.modelo.MedicoDAO;
import com.hugodiaz.seminariovet.modelo.SesionUsuario;
import com.hugodiaz.seminariovet.modelo.UsuarioDAO;
import com.hugodiaz.seminariovet.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ValidarLogin extends HttpServlet {

    UsuarioDAO userdao = new UsuarioDAO();
    SesionUsuario ses = new SesionUsuario();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValidarLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidarLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            //response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        if (accion != null && accion.equalsIgnoreCase("Ingresar")) {
            doPost(request,response);
        } else {
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idpersona;
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String user = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");
            ses = userdao.validar(user, pass);
            
            if (ses.getUsuario() != null) {
                idpersona = ses.getIdpersona();
                if (idpersona > 0 ) {
                    HttpSession sesio = request.getSession();
                    
                    String srol = ses.getUsuario().getRol().getName().toLowerCase();
                    request.setAttribute("rol", srol);
                    sesio.setAttribute("rol", srol);
                    
                    // instancio el perfil segun el rol recibido
                    switch (srol) {
                        case "medico" -> {
                            MedicoDAO mdao = new MedicoDAO();
                            Medico med = mdao.GetMedico(idpersona);
                            
                            // asocio la persona con el usuario
                            med.setUsuario(ses.getUsuario());
                            
                            request.setAttribute("profile", med);
                            sesio.setAttribute("profile", med);
                        }
                        case "administrativo" -> {
                            AdministrativoDAO adao = new AdministrativoDAO();
                            Administrativo adm = adao.GetAdministrativo(idpersona);
                            
                            adm.setUsuario(ses.getUsuario());
                            
                            request.setAttribute("profile", adm);
                            sesio.setAttribute("profile", adm);
                        }
                        case "directivo" -> {
                            DirectivoDAO ddao = new DirectivoDAO();
                            Directivo direc = ddao.GetDirectivo(idpersona);
                            
                            direc.setUsuario(ses.getUsuario());
                            
                            request.setAttribute("profile", direc);
                            sesio.setAttribute("profile", direc);
                        }
                        case "auxiliar medico" -> {
                            AuxiliarMedicoDAO axdao = new AuxiliarMedicoDAO();
                            AuxiliarMedico aux = axdao.GetAuxiliarMedico(idpersona);
                            
                            aux.setUsuario(ses.getUsuario());
                            
                            request.setAttribute("profile", aux);
                            sesio.setAttribute("profile", aux);
                        }
                        default -> {
                            request.setAttribute("profile", null);
                            sesio.setAttribute("profile", null);
                        }
                    }
                    String menu = ses.getUsuario().getRol().getMenu_gral();
                    request.getRequestDispatcher("ControladorMenu?menu=" + menu).forward(request, response);
                } else {
                    String mensajebad = Util.construyeMensaje("El usuario o la contraseña son incorrectos", "danger");
                    request.getSession().setAttribute("mensajelogin", mensajebad);
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }
            } else {
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
        } else {
           //request.getRequestDispatcher("index.jsp").forward(request, response);
           response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
