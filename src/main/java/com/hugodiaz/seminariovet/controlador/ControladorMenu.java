package com.hugodiaz.seminariovet.controlador;


import com.hugodiaz.seminariovet.modelo.Ave;
import com.hugodiaz.seminariovet.modelo.AveDAO;
import com.hugodiaz.seminariovet.modelo.Canino;
import com.hugodiaz.seminariovet.modelo.CaninoDAO;
import com.hugodiaz.seminariovet.modelo.EspecieDAO;
import com.hugodiaz.seminariovet.modelo.Felino;
import com.hugodiaz.seminariovet.modelo.FelinoDAO;
import com.hugodiaz.seminariovet.modelo.FilaTurno;
import com.hugodiaz.seminariovet.modelo.FilaTurnoDAO;
import com.hugodiaz.seminariovet.modelo.PacienteDAO;
import com.hugodiaz.seminariovet.modelo.Persona;
import com.hugodiaz.seminariovet.modelo.PuestoFilaTurnoDAO;
import com.hugodiaz.seminariovet.modelo.RazaDAO;
import com.hugodiaz.seminariovet.modelo.ReferidoDAO;
import com.hugodiaz.seminariovet.modelo.Tutor;
import com.hugodiaz.seminariovet.modelo.TutorDAO;
import com.hugodiaz.seminariovet.util.Util;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public class ControladorMenu extends HttpServlet {
    
    Tutor tut = new Tutor();
    FilaTurno fila_turno = new FilaTurno();
    
    TutorDAO tutdao = new TutorDAO();
    PacienteDAO pacdao = new PacienteDAO();
    ReferidoDAO refdao = new ReferidoDAO();
    EspecieDAO espdao = new EspecieDAO();
    CaninoDAO candao = new CaninoDAO();
    FelinoDAO feldao = new FelinoDAO();
    AveDAO avedao = new AveDAO();
    RazaDAO razdao = new RazaDAO();
    PuestoFilaTurnoDAO pftdao = new PuestoFilaTurnoDAO();
    FilaTurnoDAO ftdao = new FilaTurnoDAO();
    
    String mensajeerror;
    //Util libreria = new Util();
    
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
        //String srol = (String) request.getAttribute("rol"); 
        HttpSession sesio = request.getSession();
        
        String srol = (String) sesio.getAttribute("rol");
        if (srol == null) { // no esta logueado
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");
        if (menu == null) { // incorrecto parametro
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        if (menu != null && menu.equalsIgnoreCase("salir")) {
            sesio.invalidate();
            response.sendRedirect(request.getContextPath()+"/");
        }

        if (menu != null && menu.equalsIgnoreCase("home")) {
            Persona p = (Persona) sesio.getAttribute("profile");
            String menu_general = p.getMenuGeneral();
            response.sendRedirect(request.getContextPath()+"/"+menu_general+".jsp");
        }
        
        
        // estos menus reenvian a sendRedirect para cambiar la url de la barra de direcciones
        if (menu != null && menu.equalsIgnoreCase("menu_medico")) {
            //request.getRequestDispatcher("menu_medico.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/menu_medico.jsp");
        }
        if (menu != null && menu.equalsIgnoreCase("menu_administrativo")) {
            //request.getRequestDispatcher("menu_medico.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/menu_administrativo.jsp");
        }
        
        
        if (menu != null && menu.equalsIgnoreCase("tutores")) {
            
            switch (accion) {
                case "Listar","Cancelar" -> {
                    List lista = tutdao.listar();
                    request.setAttribute("tutores", lista);
                    String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                    request.setAttribute("renderBotonAgregar", btnAg);
                }
                case "preListar" -> {  // es cuando estoy editando, antes de dar actualizar
                    List lista = tutdao.listar();
                    request.setAttribute("tutores", lista);
                }
                case "Agregar" -> {
                    String dni = request.getParameter("txtDni").trim();
                    String nombres = request.getParameter("txtNombres");
                    String apellido = request.getParameter("txtApellido");
                    String direccion = request.getParameter("txtDireccion");
                    String email = request.getParameter("txtEmail");
                    String telefono = request.getParameter("txtTelefono").trim();
                    String comentario = request.getParameter("txtComentario");
                    
                    if (!dni.equalsIgnoreCase("")) {
                        if (!dni.matches("^[0-9]+$")) { // regla de negocio: el dni debe contener solo digitos.

                            mensajeerror = Util.construyeMensaje("El Dni o cuit solo debe contener digitos", "danger");
                            request.setAttribute("mensajeformtutor", mensajeerror);
                            request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=Listar").forward(request, response);
                            return;
                        }
                    }
                    if (!telefono.equalsIgnoreCase("")) {
                        if (!telefono.matches("^[0-9]+$")) { // regla de negocio: el telefono debe contener solo digitos

                            mensajeerror = Util.construyeMensaje("El telefono solo debe contener digitos", "danger");
                            request.setAttribute("mensajeformtutor", mensajeerror);
                            request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=Listar").forward(request, response);
                            return;
                        }
                    }

                    tut.setDni(dni);
                    tut.setNombres(nombres);
                    tut.setApellido(apellido);
                    tut.setDireccion(direccion);
                    tut.setEmail(email);
                    tut.setTelefono(telefono);
                    tut.setComentario(comentario);
                    tutdao.agregar(tut);
                    request.setAttribute("mensajeformtutor", ""); // para evitar arrastrar errores de formulario anteriores
                    String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                    request.setAttribute("renderBotonAgregar", btnAg);
                    
                    request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=Listar").forward(request, response);
                        
                }
                case "Editar" -> {
                    int idt = Integer.parseInt(request.getParameter("id"));
                    tut = tutdao.listar(idt);
                    request.setAttribute("tutor", tut);
                    String btnAc = """
                                   <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                                   """;
                    request.setAttribute("renderBotonActualizar", btnAc);
                    request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=preListar").forward(request, response);
                }
                case "Actualizar" -> {
                    int idt = Integer.parseInt(request.getParameter("txtIdtutor"));
                    String dni = request.getParameter("txtDni").trim();
                    String nombres = request.getParameter("txtNombres");
                    String apellido = request.getParameter("txtApellido");
                    String direccion = request.getParameter("txtDireccion");
                    String email = request.getParameter("txtEmail");
                    String telefono = request.getParameter("txtTelefono").trim();
                    String comentario = request.getParameter("txtComentario");
                    
                    if (!dni.equalsIgnoreCase("")) {
                        if (!dni.matches("^[0-9]+$")) { // regla de negocio: el dni debe contener solo digitos.

                            mensajeerror = Util.construyeMensaje("El Dni o cuit solo debe contener digitos", "danger");
                            request.setAttribute("mensajeformtutor", mensajeerror);
                            request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=Listar").forward(request, response);
                            return;
                        }
                    }
                    if (!telefono.equalsIgnoreCase("")) {
                        if (!telefono.matches("^[0-9]+$")) { // regla de negocio: el telefono debe contener solo digitos

                            mensajeerror = Util.construyeMensaje("El telefono solo debe contener digitos", "danger");
                            request.setAttribute("mensajeformtutor", mensajeerror);
                            request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=Listar").forward(request, response);
                            return;
                        }
                    }
                    tut.setIdtutor(idt);
                    tut.setDni(dni);
                    tut.setNombres(nombres);
                    tut.setApellido(apellido);
                    tut.setDireccion(direccion);
                    tut.setEmail(email);
                    tut.setTelefono(telefono);
                    tut.setComentario(comentario);
                    tutdao.actualizar(tut);
                    String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                    request.setAttribute("renderBotonAgregar", btnAg);
                    
                    request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=Listar").forward(request, response);
                }
                case "Delete" -> {
                    int idt = Integer.parseInt(request.getParameter("id"));
                    tutdao.delete(idt);
                    String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                    request.setAttribute("renderBotonAgregar", btnAg);
                    request.getRequestDispatcher("ControladorMenu?menu=Tutores&accion=Listar").forward(request, response);
                }
                default -> {
                    throw new AssertionError();
                }
            }
            request.getRequestDispatcher("tutor.jsp").forward(request, response);
        }
        if (menu != null && menu.equalsIgnoreCase("mascotas")) {
            
            switch (accion) {
                case "Listar" -> {
                    response.sendRedirect(request.getContextPath()+"/menu_mascota.jsp");
                    return;
                }
                case "ListarMascota" -> {
                    List listapaciente = pacdao.listar();
                    sesio.setAttribute("pacientes", listapaciente);
                    
                    HashMap listaespecies = Util.getEspecies();
                    sesio.setAttribute("especies", listaespecies);
                    
                    List listareferido = refdao.listar();
                    sesio.setAttribute("referidos", listareferido);

                    response.sendRedirect(request.getContextPath()+"/mascota.jsp");
                    return;
                }
                case "Cancelar" -> {
                    response.sendRedirect(request.getContextPath()+"/mascota_bytutor2.jsp");
                    return;
                }
                case "preListar" -> {  // es cuando estoy editando, antes de dar actualizar

                    int idp = Integer.parseInt(request.getParameter("idp"));
                    int idt = Integer.parseInt(request.getParameter("idt"));
                    List listapaciente = pacdao.listar(idt);
                    sesio.setAttribute("pacientes", listapaciente);
                    int idespecie = pacdao.getEspeciePaciente(idp);
                    sesio.setAttribute("debus",idespecie);
                    List listaraza = razdao.listar(idespecie);
                    sesio.setAttribute("razas",listaraza);
                    
                    response.sendRedirect(request.getContextPath()+"/mascota_bytutor4.jsp");
                    return;
                }
                case "preListar2" -> {  // es cuando estoy editando, antes de dar actualizar
                    //List lista = tutdao.listar();
                    //request.setAttribute("tutores", lista);
                    int idp = Integer.parseInt(request.getParameter("idp"));
                    List listapaciente = pacdao.listar();
                    sesio.setAttribute("pacientes", listapaciente);
                    int idespecie = pacdao.getEspeciePaciente(idp);
                    sesio.setAttribute("debus",idespecie);
                    
                    HashMap listaespecies = Util.getEspecies();
                    sesio.setAttribute("especies", listaespecies);
                    
                    List listareferido = refdao.listar();
                    sesio.setAttribute("referidos", listareferido);
                    
                    List listaraza = razdao.listar(idespecie);
                    sesio.setAttribute("razas",listaraza);
                    
                    response.sendRedirect(request.getContextPath()+"/mascota4.jsp");
                    return;
                }
                case "Volver" -> { //vuelvo a buscar un tutor. limpio la grilla.
                    List listapaciente = null;
                    sesio.setAttribute("pacientes", listapaciente);
                    response.sendRedirect(request.getContextPath()+"/mascota_bytutor1.jsp");
                    return;
                    
                }
                case "Nueva" -> {
                    response.sendRedirect(request.getContextPath()+"/mascota_bytutor3.jsp");
                    return;
                }
                case "ByTutor"-> {
                    //List listaunpaciente = pacdao.getListUnPacienteBlank("Canino");
                    //sesio.setAttribute("listaunpaciente", listaunpaciente);
                    sesio.setAttribute("pacientes", "");
                    response.sendRedirect(request.getContextPath()+"/mascota_bytutor1.jsp");
                    return;
                }
                case "ByTutor2" -> {
                    //List listaunpaciente = pacdao.getListUnPacienteBlank("Canino");
                    //sesio.setAttribute("listaunpaciente", listaunpaciente);
                    response.sendRedirect(request.getContextPath()+"/mascota_bytutor2.jsp");
                    return;
                }
                case ">" -> {
                    if (request.getParameter("codigotutor").equals("")) {
                        response.sendRedirect(request.getContextPath()+"/mascota_bytutor1.jsp");
                        return;
                    }
                    int idt = Integer.parseInt(request.getParameter("codigotutor"));
                    
                    tut = tutdao.listar(idt);
                    sesio.setAttribute("tutor", tut);
                    if (tut.getApellido() != null && !tut.getApellido().equals("")) {
                        String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                        sesio.setAttribute("renderBotonAgregar", btnAg);
                    } else {
                        sesio.setAttribute("renderBotonAgregar", "");
                    }
                    
                    List listapaciente = pacdao.listar(idt);
                    sesio.setAttribute("pacientes", listapaciente);
                    
                    HashMap listaespecies = Util.getEspecies();
                    sesio.setAttribute("especies", listaespecies);
                    
                    List listareferido = refdao.listar();
                    sesio.setAttribute("referidos", listareferido);
                    //request.setAttribute("tutor", tut);
                    //response.sendRedirect(request.getContextPath()+"/mascota_bytutor.jsp");
                    request.getRequestDispatcher("ControladorMenu?menu=Mascotas&accion=ByTutor2").forward(request, response);
                    return;
                    
                }
                case "Agregar" -> {
                    String especie = request.getParameter("spcSelect");
                    String raza = request.getParameter("razaSelect");
                    String nombre = request.getParameter("txtNombre").trim();
                    String sexo = request.getParameter("sexoSelect");
                    String fnac = request.getParameter("txtFechanacimiento").trim();
                    String peso = request.getParameter("txtPeso").trim();
                    String microchip = request.getParameter("txtMicrochip").trim();
                    String referido = request.getParameter("refSelect");
                    String aspecto = request.getParameter("txtAspecto").trim();
                    
                    String sespecie = espdao.getNombre(Integer.parseInt(especie));
                    
                    switch (sespecie) {
                        case "Canino" -> {   
                            Canino can = new Canino();
                            can.setIdrazaespecie(Integer.parseInt(raza));
                            can.setNombre(nombre);
                            can.setSexo(sexo);
                            can.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                can.setPeso(Double.parseDouble(peso));
                            }
                            can.setMicrochip(microchip);
                            can.setIdreferido(Integer.parseInt(referido));
                            can.setPelaje(aspecto);
                            can.setIdtutor(tut.getIdtutor());
                            candao.agregar(can);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                            String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                            sesio.setAttribute("renderBotonAgregar", btnAg);
                        }
                        case "Felino" -> {   
                            Felino fel = new Felino();
                            fel.setIdrazaespecie(Integer.parseInt(raza));
                            fel.setNombre(nombre);
                            fel.setSexo(sexo);
                            fel.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                fel.setPeso(Double.parseDouble(peso));
                            }
                            fel.setMicrochip(microchip);
                            fel.setIdreferido(Integer.parseInt(referido));
                            fel.setPelaje(aspecto);
                            fel.setIdtutor(tut.getIdtutor());
                            feldao.agregar(fel);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                            String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                            sesio.setAttribute("renderBotonAgregar", btnAg);
                        }
                        case "Ave" -> {   
                            Ave ave = new Ave();
                            ave.setIdrazaespecie(Integer.parseInt(raza));
                            ave.setNombre(nombre);
                            ave.setSexo(sexo);
                            ave.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                ave.setPeso(Double.parseDouble(peso));
                            }
                            ave.setMicrochip(microchip);
                            ave.setIdreferido(Integer.parseInt(referido));
                            ave.setPlumaje(aspecto);
                            ave.setIdtutor(tut.getIdtutor());
                            avedao.agregar(ave);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                            String btnAg = """
                                   <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                   """;
                            sesio.setAttribute("renderBotonAgregar", btnAg);
                        }

                    }
                    List listapaciente = pacdao.listar(tut.getIdtutor());
                    sesio.setAttribute("pacientes", listapaciente);
                    
                    request.getRequestDispatcher("ControladorMenu?menu=Mascotas&accion=Nueva").forward(request, response);
                    return;
                }
                case "Editar" -> {
                    int idp = Integer.parseInt(request.getParameter("id"));
                    Tutor tut1 = (Tutor) sesio.getAttribute("tutor");
                    int idt = tut1.getIdtutor();
                    List listaunpaciente = pacdao.getListUnPaciente(idp);
                    
                    sesio.setAttribute("listaunpaciente", listaunpaciente);
                    String btnAc = """
                                   <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                                   """;
                    request.setAttribute("renderBotonActualizar", btnAc);
                    sesio.setAttribute("renderBotonActualizar", btnAc);
                    request.getRequestDispatcher("ControladorMenu?menu=Mascotas&accion=preListar&idt=" + idt +"&idp=" + idp).forward(request, response);
                    return;
                }
                case "Editar2" -> {
                    int idp = Integer.parseInt(request.getParameter("id"));
                    List listaunpaciente = pacdao.getListUnPaciente(idp);
                    
                    sesio.setAttribute("listaunpaciente", listaunpaciente);
                    String btnAc = """
                                   <input type="submit" name="accion" value="Actualizar2" class="btn btn-success">
                                   """;
                    request.setAttribute("renderBotonActualizar", btnAc);
                    sesio.setAttribute("renderBotonActualizar", btnAc);
                    request.getRequestDispatcher("ControladorMenu?menu=Mascotas&accion=preListar2&idp=" + idp).forward(request, response);
                    return;
                }
                case "Actualizar" -> {
                    int idp = Integer.parseInt(request.getParameter("txtIdpaciente"));
                    String especie = request.getParameter("spcSelect");
                    String raza = request.getParameter("razaSelect");
                    String nombre = request.getParameter("txtNombre").trim();
                    String sexo = request.getParameter("sexoSelect");
                    String fnac = request.getParameter("txtFechanacimiento").trim();
                    String peso = request.getParameter("txtPeso").trim();
                    String microchip = request.getParameter("txtMicrochip").trim();
                    String referido = request.getParameter("refSelect");
                    String aspecto = request.getParameter("txtAspecto").trim();
                    
                    String sespecie = espdao.getNombre(Integer.parseInt(especie));
                    switch (sespecie) {
                        case "Canino" -> {
                            Canino can = new Canino();
                            can.setIdpaciente(idp);
                            can.setIdrazaespecie(Integer.parseInt(raza));
                            can.setNombre(nombre);
                            can.setSexo(sexo);
                            can.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                can.setPeso(Double.parseDouble(peso));
                            }
                            can.setMicrochip(microchip);
                            can.setIdreferido(Integer.parseInt(referido));
                            can.setPelaje(aspecto);
                            can.setIdtutor(tut.getIdtutor());
                            candao.actualizar(can);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                        }
                        case "Felino" -> {
                            Felino fel = new Felino();
                            fel.setIdpaciente(idp);
                            fel.setIdrazaespecie(Integer.parseInt(raza));
                            fel.setNombre(nombre);
                            fel.setSexo(sexo);
                            fel.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                fel.setPeso(Double.parseDouble(peso));
                            }
                            fel.setMicrochip(microchip);
                            fel.setIdreferido(Integer.parseInt(referido));
                            fel.setPelaje(aspecto);
                            fel.setIdtutor(tut.getIdtutor());
                            feldao.actualizar(fel);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                        }
                        case "Ave" -> {
                            Ave ave = new Ave();
                            ave.setIdpaciente(idp);
                            ave.setIdrazaespecie(Integer.parseInt(raza));
                            ave.setNombre(nombre);
                            ave.setSexo(sexo);
                            ave.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                ave.setPeso(Double.parseDouble(peso));
                            }
                            ave.setMicrochip(microchip);
                            ave.setIdreferido(Integer.parseInt(referido));
                            ave.setPlumaje(aspecto);
                            ave.setIdtutor(tut.getIdtutor());
                            avedao.actualizar(ave);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                        }
                    }
                    List listapaciente = pacdao.listar(tut.getIdtutor());
                    sesio.setAttribute("pacientes", listapaciente);
                    
                    request.getRequestDispatcher("ControladorMenu?menu=Mascotas&accion=ByTutor2").forward(request, response);
                    return;
                    
                }
                case "Actualizar2" -> {
                    int idp = Integer.parseInt(request.getParameter("txtIdpaciente"));
                    int idt = Integer.parseInt(request.getParameter("txtIdtutor"));
                    String especie = request.getParameter("spcSelect");
                    String raza = request.getParameter("razaSelect");
                    String nombre = request.getParameter("txtNombre").trim();
                    String sexo = request.getParameter("sexoSelect");
                    String fnac = request.getParameter("txtFechanacimiento").trim();
                    String peso = request.getParameter("txtPeso").trim();
                    String microchip = request.getParameter("txtMicrochip").trim();
                    String referido = request.getParameter("refSelect");
                    String aspecto = request.getParameter("txtAspecto").trim();
                    
                    String sespecie = espdao.getNombre(Integer.parseInt(especie));
                    switch (sespecie) {
                        case "Canino" -> {
                            Canino can = new Canino();
                            can.setIdpaciente(idp);
                            can.setIdrazaespecie(Integer.parseInt(raza));
                            can.setNombre(nombre);
                            can.setSexo(sexo);
                            can.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                can.setPeso(Double.parseDouble(peso));
                            }
                            can.setMicrochip(microchip);
                            can.setIdreferido(Integer.parseInt(referido));
                            can.setPelaje(aspecto);
                            can.setIdtutor(idt);
                            candao.actualizar(can);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                        }
                        case "Felino" -> {
                            Felino fel = new Felino();
                            fel.setIdpaciente(idp);
                            fel.setIdrazaespecie(Integer.parseInt(raza));
                            fel.setNombre(nombre);
                            fel.setSexo(sexo);
                            fel.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                fel.setPeso(Double.parseDouble(peso));
                            }
                            fel.setMicrochip(microchip);
                            fel.setIdreferido(Integer.parseInt(referido));
                            fel.setPelaje(aspecto);
                            fel.setIdtutor(idt);
                            feldao.actualizar(fel);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                        }
                        case "Ave" -> {
                            Ave ave = new Ave();
                            ave.setIdpaciente(idp);
                            ave.setIdrazaespecie(Integer.parseInt(raza));
                            ave.setNombre(nombre);
                            ave.setSexo(sexo);
                            ave.setFecha_nacimiento(fnac);
                            if (!peso.equalsIgnoreCase("")) {
                                ave.setPeso(Double.parseDouble(peso));
                            }
                            ave.setMicrochip(microchip);
                            ave.setIdreferido(Integer.parseInt(referido));
                            ave.setPlumaje(aspecto);
                            ave.setIdtutor(idt);
                            avedao.actualizar(ave);
                            sesio.setAttribute("mensajeformpaciente", ""); // para evitar arrastrar errores de formulario anteriores
                        }
                    }
                    
                    List listapaciente = pacdao.listar();
                    sesio.setAttribute("pacientes", listapaciente);
                    
                    request.getRequestDispatcher("ControladorMenu?menu=Mascotas&accion=ListarMascota").forward(request, response);
                    return;
                    
                }
                
                case "Delete" -> {
                    int idp = Integer.parseInt(request.getParameter("id"));
                    pacdao.delete(idp);
                    
                    tut = (Tutor) sesio.getAttribute("tutor");
                    
                    List listapaciente = pacdao.listar(tut.getIdtutor());
                    sesio.setAttribute("pacientes", listapaciente);
                    //request.setAttribute("renderBotonAgregar", btnAg);
                    request.getRequestDispatcher("ControladorMenu?menu=Mascotas&accion=ByTutor2").forward(request, response);
                    return;
                }
                default -> {
                    throw new AssertionError();
                }
            }
            //request.getRequestDispatcher("menu_mascota.jsp").forward(request, response);
            //response.sendRedirect(request.getContextPath()+"/menu_mascota.jsp");
        }
        if (menu != null && menu.equalsIgnoreCase("gabinete")) {
            switch (accion) {
                case "Listar" -> {
                    response.sendRedirect(request.getContextPath()+"/menu_gabinete.jsp");
                    return;
                }
                case "FilaEspera" -> {
                    // primero vacio la cola
                    fila_turno.clear();
                    
                    fila_turno = ftdao.listar(Util.getFechaHoy());
                    
                    fila_turno.sort();
                    
                    List lista = fila_turno.listarPuestos();
                    request.setAttribute("fila", lista);
                    
                    String btnAg = """
                                   <input type="submit" name="accion" value="Atender" class="btn btn-success">
                                   """;
                    request.setAttribute("renderBotonAgregar", btnAg);
                }
                case "Atender" -> {
                    int idp = Integer.parseInt(request.getParameter("idp"));
                    List listaunpaciente = pacdao.getListUnPaciente(idp);
                    
                    sesio.setAttribute("listaunpaciente", listaunpaciente);
                                        HashMap listaespecies = Util.getEspecies();
                    sesio.setAttribute("especies", listaespecies);
                    
                    List listareferido = refdao.listar();
                    sesio.setAttribute("referidos", listareferido);
                    int idespecie = pacdao.getEspeciePaciente(idp);
                    List listaraza = razdao.listar(idespecie);
                    sesio.setAttribute("razas",listaraza);

                    //request.getRequestDispatcher("ControladorMenu?menu=HistoriaClinica").forward(request, response);
                    response.sendRedirect(request.getContextPath()+"/historiaclinica.jsp");
                    return;
                }
                default -> {
                    throw new AssertionError();
                }
            }
            request.getRequestDispatcher("gabinete.jsp").forward(request, response);

        }
        if (menu != null && menu.equalsIgnoreCase("historiaclinica")) {
            //request.getRequestDispatcher("ControladorMenu?menu=Home").forward(request, response);
            response.sendRedirect(request.getContextPath()+"historiaclinica.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
    }


}
