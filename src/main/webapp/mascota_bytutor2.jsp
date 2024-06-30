<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mascota desde Tutor 2</title>
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
                                    <input type="number" name="codigotutor" required value="${tutor.getIdtutor()}" class="form-control">
                                    <input type="submit" name="accion" value=">" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-7">
                                    <input type="text" readonly name="nombretutor" value="${tutor.getApellidoNombres()}" class="form-control">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card">
                    <div class="card-body">
                        <form action="ControladorMenu?menu=Mascotas" method="POST">
                        <input type="submit" name="accion" value="Nueva" class="btn btn-info">
                        <input type="submit" name="accion" value="Volver" class="btn btn-secondary">
                        </form>
                    </div>
                </div>
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
                                        <a class="btn btn-danger apeligro" href="ControladorMenu?menu=Mascotas&accion=Delete&id=${pac.getIdpaciente()}&name=${pac.getNombre()}">Delete</a>
                                        </td>                                                                      
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
                        
   
    </body>
    <script>
    	function showPromptYesNo(msg,ttle,unrl,navFnYes) {
    		new $.Zebra_Dialog(
    			msg,
    			{
        		type: "warning",
        		title: ttle,
        		buttons: ["Si", "No"],
        		onClose: function(caption) {
 					if (caption != "" && caption == "Si") {
 						navFnYes(unrl);
 					}
        		}
    		}
		);
	};

	function navigateTo(unrl) {
  		window.location.href = unrl;
  	};
  
      $(document).ready(function() {
      var anchors = document.getElementsByClassName("apeligro");

	
		Array.from(anchors).forEach(function (anchor) {
    		anchor.addEventListener("click", function (obj) {
        		var href = $(this).attr('href');
        		const url = new URL('http://localhost:8080/SeminarioVet/' + href); 
				var name_mascota = url.searchParams.get('name');
        		var msg = 'Va a borrar la mascota ' + name_mascota + '. Confirma?';
        		showPromptYesNo(msg,'Atencion',url,navigateTo);
                obj.stopPropagation();
		        obj.preventDefault();
	    	});
		});

    	
	 });
    </script>
    
</html>
