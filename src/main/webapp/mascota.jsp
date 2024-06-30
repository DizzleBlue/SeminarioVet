<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mascota</title>
        <link href="css/bootstrap_5.3.3.min.css" rel="stylesheet">
        <script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/bootstrap_5.3.3.bundle.min.js"></script>
	<link rel="stylesheet" href="css/flat/zebra_dialog.min.css" type="text/css">
	<script src="js/zebra_dialog.min.js"></script>
    </head>
    <body>
        <h1>Mascota</h1>
        <div class="d-flex">
            <div class="col-sm-3">
                                
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
                                <option value="${esp.getKey()}">${esp.getValue()}</option>
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
                            <input type="text"  name="txtNombre" value="" class="form-control" required>
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
                            <input type="date"  name="txtFechanacimiento" value="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Peso en kg</label>
                            <input type="text" name="txtPeso" value="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Microchip</label>
                            <input type="text"  name="txtMicrochip" value="" class="form-control">
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
                            <input type="text"  name="txtAspecto" value="" class="form-control">
                        </div>
                        <input type="hidden" name="txtIdpaciente" value="${paciente.getIdpaciente()}">
                        ${mensajeformpaciente}
                        <br>
                        ${renderBotonAgregar}
                        ${renderBotonActualizar}
                        <input type="submit" name="accion" value="Cancelar" class="btn btn-secondary" formnovalidate>
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
                                    <th>Tutor</th>
                                    <th>Nombre</th>
                                    <th>Especie</th>
                                    <th>Raza</th>
                                    <th>Aspecto</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pac" items="${pacientes}">
                                    <tr>
                                        <td>${pac.getNombreTutor()}</td>
                                        <td>${pac.getNombre()}</td>
                                        <td>${pac.getTipoPaciente()}</td>
                                        <td>${pac.getRaza()}</td>
                                        <td>${pac.getAspecto()}</td>  <!-- aca se utiliza polimorfismo al recibir -->
                                                                      <!-- una lista de objetos tipo paciente (abstractos) -->
                                                                      <!-- pero el metodo getAspecto() se implementa -->
                                                                      <!-- en las clases heredadas -->
                                        <td>
                                        <a class="btn btn-warning" href="ControladorMenu?menu=Mascotas&accion=Editar2&id=${pac.getIdpaciente()}">Editar</a>
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
                    
    </body>
</html>
