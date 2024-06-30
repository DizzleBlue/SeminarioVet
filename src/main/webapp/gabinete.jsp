<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sala de Espera</title>
        <link href="css/bootstrap_5.3.3.min.css" rel="stylesheet">
        <script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/bootstrap_5.3.3.bundle.min.js"></script>
	<link rel="stylesheet" href="css/flat/zebra_dialog.min.css" type="text/css">
	<script src="js/zebra_dialog.min.js"></script>
    </head>
    <body>
        <h1>Sala de Espera</h1>
        
        <div class="d-flex">
        <div class="col-sm-12">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>NUMERO</th>
                        <th>INGRESO</th>
                        <th>TUTOR</th>
                        <th>PACIENTE</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="puesto" items="${fila}">
                    <tr>
                        <td>${puesto.getNumero_asignado()}</td>
                        <td>${puesto.getFechahora_ingreso()}</td>
                        <td>${puesto.getTutor().getApellido()}</td>
                        <td>${puesto.getListaUnPaciente().get(0).getNombre()}</td>
                        <td>
                            <a class="btn btn-success" href="ControladorMenu?menu=Gabinete&accion=Atender&idp=${puesto.getListaUnPaciente().get(0).getIdpaciente()}" target="myFrame">Atender</a>
                            <a class="btn btn-danger apeligro" href="ControladorMenu?menu=Gabinete&accion=Delete&id=&id=${puesto.getId()}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            
        </div>
        </div>
                    
    </body>
</html>
