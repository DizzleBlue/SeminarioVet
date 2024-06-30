<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="valida_sesion.jsp" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mundo Exotico</title>
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
        <link href="css/bootstrap_5.3.3.min.css" rel="stylesheet">
        <script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/bootstrap_5.3.3.bundle.min.js"></script>
	<link rel="stylesheet" href="css/flat/zebra_dialog.min.css" type="text/css">
	<script src="js/zebra_dialog.min.js"></script>
    </head>
    <body>
        <nav class="navbar bg-primary navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="ControladorMenu?menu=Tutores&accion=Listar" target="myFrame">Tutores</a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="ControladorMenu?menu=Mascotas&accion=Listar">Mascotas</a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="ControladorMenu?menu=Turnos" target="myFrame">Turnos</a>
                        </li>
                    </ul>
                    <div class="dropdown">
                        <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${profile.getApellidoNombres()}
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark text-center">
                            <li>
                                <a class="dropdown-item active" href="#">
                                    <img src="img/user.png" alt="" width="60" height="60"/>
                                </a>
                            </li>
                            <li><a class="dropdown-item" href="#">${profile.getUsuario().getLogin()}</a></li>
                            <li><a class="dropdown-item" href="#">${profile.getEmail()}</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <form action="ControladorMenu" method="POST">
                                    <button type="submit" name="accion" value="Salir" class="dropdown-item">Salir</button>
                                    <input type="hidden" name="menu" value="salir"/>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <div class="m-4" style="height: 550px;">
            <iframe name="myFrame" style="height: 100%; width: 100%; border: none">

            </iframe>
        </div>
    </body>
</html>
