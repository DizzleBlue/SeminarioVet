<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tutor</title>
        <link href="css/bootstrap_5.3.3.min.css" rel="stylesheet">
        <script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/bootstrap_5.3.3.bundle.min.js"></script>
	<link rel="stylesheet" href="css/flat/zebra_dialog.min.css" type="text/css">
	<script src="js/zebra_dialog.min.js"></script>
    </head>
    <body>
        <div class="d-flex">
        <div class="card col-sm-4">
            <div class="card-body">
                <form action="ControladorMenu?menu=Tutores" method="POST">
                    <div class="form-group">
                        <label>DNI</label>
                        <input type="text" name="txtDni" maxlength="11" value="${tutor.getDni()}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Apellido</label>
                        <input type="text"  name="txtApellido" value="${tutor.getApellido()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Nombres</label>
                        <input type="text"  name="txtNombres" value="${tutor.getNombres()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Direccion</label>
                        <input type="text"  name="txtDireccion" value="${tutor.getDireccion()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="txtEmail" value="${tutor.getEmail()}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Telefono</label>
                        <input type="text" required name="txtTelefono" value="${tutor.getTelefono()}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Comentarios</label>
                        <input type="text" name="txtComentario" value="${tutor.getComentario()}" class="form-control">
                    </div>
                    <input type="hidden" name="txtIdtutor" value="${tutor.getIdtutor()}">
                    ${mensajeformtutor}
                    <br>
                    ${renderBotonAgregar}
                    ${renderBotonActualizar}
                    <input type="submit" name="accion" value="Cancelar" class="btn btn-secondary">
                </form>
            </div>
        </div>
        <div class="col-sm-8">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>APELLIDO</th>
                        <th>NOMBRES</th>
                        <th>DIRECCION</th>
                        <th>TELEFONO</th>
                        <th>EMAIL</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="tut" items="${tutores}">
                    <tr>
                        <td>${tut.getIdtutor()}</td>
                        <td>${tut.getApellido()}</td>
                        <td>${tut.getNombres()}</td>
                        <td>${tut.getDireccion()}</td>
                        <td>${tut.getTelefono()}</td>
                        <td>${tut.getEmail()}</td>
                        <td>
                            <a class="btn btn-warning" href="ControladorMenu?menu=Tutores&accion=Editar&id=${tut.getIdtutor()}">Editar</a>
                            <a class="btn btn-danger apeligro" href="ControladorMenu?menu=Tutores&accion=Delete&id=${tut.getIdtutor()}&name=${tut.getDniApellidoNombres()}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        </div>
        <script type="text/javascript">
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
				var name_tutor = url.searchParams.get('name');
        		var msg = 'Va a borrar el tutor ' + name_tutor + '. Confirma?';
        		showPromptYesNo(msg,'Atencion',url,navigateTo);
                obj.stopPropagation();
		        obj.preventDefault();
	    	});
		});                
            });
        </script>
                    
    </body>
</html>
