<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap_5.3.3.min.css" rel="stylesheet">
        <script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/bootstrap_5.3.3.bundle.min.js"></script>
	<link rel="stylesheet" href="css/flat/zebra_dialog.min.css" type="text/css">
	<script src="js/zebra_dialog.min.js"></script>
    </head>
    <body>
        <h1>HC</h1>
            <div class="d-flex">
            <div class="col-sm-3">
                                
                <c:forEach var="onepac" items="${listaunpaciente}">
                
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">
                            <label><b>Mascota</b></label>
                        </div>
                        <br>
                        
                        <div class="form-group">
                            <label>Especie</label>
                            <select id="spcSelect" name="spcSelect" class="form-select" disabled>
                                <c:forEach var="esp" items="${especies}">
                                    <c:choose>
                                        <c:when test="${onepac.getTipoPaciente() == esp.getValue()}">
                                            <option selected value="${esp.getKey()}">${esp.getValue()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${esp.getKey()}">${esp.getValue()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Raza</label>
                            <select id="razaSelect" name="razaSelect" class="form-select" disabled>
                                <c:forEach var="raz" items="${razas}">
                                    <c:choose>
                                        <c:when test="${onepac.getRaza() == raz.getNombre()}">
                                            <option selected value="${raz.getIdraza()}">${raz.getNombre()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${raz.getIdraza()}">${raz.getNombre()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text"  name="txtNombre" value="${onepac.getNombre()}" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            <label>Sexo</label>
                            <select id="sexoSelect" name="sexoSelect" class="form-select" disabled>
                                <c:choose>
                                    <c:when test="${onepac.getSexo() == 'M'}">
                                    <option selected value="M">Macho</option>
                                    </c:when>
                                    <c:otherwise>
                                    <option value="M">Macho</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${onepac.getSexo() == 'H'}">
                                    <option selected value="H">Hembra</option>
                                    </c:when>
                                    <c:otherwise>
                                    <option value="H">Hembra</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Fecha Nacimiento</label>
                            <input type="date"  name="txtFechanacimiento" value="${onepac.getFecha_nacimiento()}" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            <label>Peso en kg</label>
                            <input type="text"  name="txtPeso" value="${onepac.getPeso()}" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            <label>Microchip</label>
                            <input type="text"  name="txtMicrochip" value="${onepac.getMicrochip()}" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            
                            <label>Referido</label>
                            <select id="refSelect" name="refSelect" class="form-select" disabled>
                                <c:choose>
                                    <c:when test="${onepac.getIdreferido() == 0}">
                                        <option value="0" selected>Seleccione..</option>
                                        <c:forEach var="ref" items="${referidos}">
                                        <option value="${ref.getIdreferido()}">${ref.getNombre()}</option>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">Seleccione..</option>
                                        <c:forEach var="ref" items="${referidos}">
                                            <c:choose>
                                            <c:when test="${onepac.getIdreferido() == ref.getIdreferido()}">
                                                <option selected value="${ref.getIdreferido()}">${ref.getNombre()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${ref.getIdreferido()}">${ref.getNombre()}</option>
                                            </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="form-group" title="color y textura de: plumaje, pelaje, escamas, etc">
                            <label>Aspecto</label>
                            <input type="text"  name="txtAspecto" value="${onepac.getAspecto()}" class="form-control" readonly>
                        </div>
                        <input type="hidden" name="txtIdpaciente" value="${onepac.getIdpaciente()}">
                    </div>
                </div>
                </c:forEach>
            </div>
            <div class="col-sm-9">
                <div clas="card">
                    <div class="card-body">
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