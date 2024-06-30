<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Veterinaria</title>
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
        <link href="css/bootstrap_5.3.3.min.css" rel="stylesheet">
        <script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/bootstrap_5.3.3.bundle.min.js"></script>
	<link rel="stylesheet" href="css/flat/zebra_dialog.min.css" type="text/css">
	<script src="js/zebra_dialog.min.js"></script>
    </head>
    <body>
        <div class="container mt-4 col-lg-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-sign" action="ValidarLogin" method="POST">
                        <div class="form-group  text-center">
                            <h3>Login</h3>
                            <img src="img/logo.png" alt="logo" width="170" height="150"/>
                            <br><br>
                            <label>Bienvenidos al Sistema</label>
                        </div>
                        <div class="form-group">
                        <label>Usuario:</label>
                        <input type="text" name="txtuser" class="form-control" value="" required>
                        </div>
                        <div class="form-group">
                        <label>Password:</label>
                        <input type="password" name="txtpass" class="form-control" value="" required>
                        </div>
                        ${mensajelogin}
                        <br>
                        <input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-block"> 
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
