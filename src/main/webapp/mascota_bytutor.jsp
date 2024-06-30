<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mascota desde Tutor 0</title>
        <link href="css/bootstrap_5.3.3.min.css" rel="stylesheet">
        <script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/bootstrap_5.3.3.bundle.min.js"></script>
	<link rel="stylesheet" href="css/flat/zebra_dialog.min.css" type="text/css">
	<script src="js/zebra_dialog.min.js"></script>
    </head>
    <body>
        <h1>Mascota desde Tutor</h1>
        <div class="d-flex">
            <div class="col-sm-3">
                <div class="card">
                    <form action="ControladorMenu?menu=Mascotas" method="POST">
                        <div class="card-body">
                            <div class="form-group">
                                <label>Seleccione Tutor</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-5 d-flex">
                                    <input type="number" name="codigotutor" value="${tutor.getIdtutor()}" class="form-control">
                                    <input type="submit" name="accion" value=">" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-7">
                                    <input type="text" name="nombretutor" value="${tutor.getApellidoNombres()}" class="form-control">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                                
                <c:forEach var="onepac" items="${listaunpaciente}">
                
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">
                            <label><b>Mascota</b></label>
                        </div>
                        <br>
                        <form action="ControladorMenu?menu=Mascotas" method="POST">
                            <input type="hidden" name="inpdebug" value="${onepac.getTipoPaciente()}">
                        <div class="form-group">
                            <label>Especie</label>
                            <select id="spcSelect" name="spcSelect" class="form-select">
                                <option value="0" selected disabled>Seleccione..</option>
                                <c:forEach var="esp" items="${especies}">
<!-- cambiar este jsp por otro -->                                        <option value="${esp.getKey()}">${esp.getValue()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Raza</label>
                            <select id="razaSelect" name="razaSelect" class="form-select">
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text"  name="txtNombre" value="${onepac.getNombre()}" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Sexo</label>
                            <select id="sexoSelect" name="sexoSelect" class="form-select" required>
                                <option value="?" disabled selected>Seleccione..</option>
                                <option value="M">Macho</option>
                                <option value="H">Hembra</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Fecha Nacimiento</label>
                            <input type="text"  name="txtFechanacimiento" value="${onepac.getFecha_nacimiento()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Peso en kg</label>
                            <input type="text"  name="txtPeso" value="${onepac.getPeso()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Microchip</label>
                            <input type="text"  name="txtMicrochip" value="${onepac.getMicrochip()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Referido</label>
                            <select id="refSelect" name="refSelect" class="form-select">
                                <option value="0" selected>Seleccione..</option>
                                <c:forEach var="ref" items="${referidos}">
                                <option value="${ref.getIdreferido()}">${ref.getNombre()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" title="color y textura de: plumaje, pelaje, escamas, etc">
                            <label>Aspecto</label>
                            <input type="text"  name="txtAspecto" value="${onepac.getAspecto()}" class="form-control">
                        </div>
                        <input type="hidden" name="txtIdpaciente" value="${paciente.getIdpaciente()}">
                        ${mensajeformpaciente}
                        <br>
                        ${renderBotonAgregar}
                        ${renderBotonActualizar}
                        <input type="submit" name="accion" value="Cancelar" class="btn btn-secondary">
                        </form>
                    </div>
                </div>
                </c:forEach>
            </div>
            <div class="col-sm-9">
                <div clas="card">
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Especie</th>
                                    <th>Raza</th>
                                    <th>Nacimiento</th>
                                    <th>Aspecto</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pac" items="${pacientes}">
                                    <tr>
                                        <td>${pac.getNombre()}</td>
                                        <td>${pac.getTipoPaciente()}</td>
                                        <td>${pac.getRaza()}</td>
                                        <td>${pac.getFecha_nacimiento()}</td>
                                        <td>${pac.getAspecto()}</td>  <!-- aca se utiliza polimorfismo al recibir -->
                                                                      <!-- una lista de objetos tipo paciente (abstractos) -->
                                                                      <!-- pero el metodo getAspecto() se implementa -->
                                                                      <!-- en las clases heredadas -->
                                        <td>
                                        <a class="btn btn-warning" href="ControladorMenu?menu=Mascotas&accion=Editar&id=${pac.getIdpaciente()}">Editar</a>
                                        <a class="btn btn-danger" href="ControladorMenu?menu=Mascotas&accion=Delete&id=${pac.getIdpaciente()}">Delete</a>
                                        </td>                                                                      
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
              var opt = $("#spcSelect option").sort(function (a,b) { return a.text.toUpperCase().localeCompare(b.text.toUpperCase()) });
              $("#spcSelect").append(opt);
              
                $("#spcSelect").change(function(){
                    var id=$("#spcSelect").val();
                    $.ajax({
                        type:"post",
                        data:{id:id},
                        url:"/SeminarioVet/getRaza",
                        success:function(result){
                            $("#razaSelect").html(result);
                        }
                    });
                });
            });
        </script>
    </body>
</html>
