<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zoo</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Zoo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" href="pages/animal-list.jsp">Állataink</a>
                <c:if test="${cookie.email == null}">
                    <a class="nav-link" href="pages/adopter-registration.jsp">Regisztráció</a>
                </c:if>
                <c:if test="${cookie.email != null}">
                    <a class='nav-link' href='#'>${cookie.email.value}</a>
                </c:if>
            </div>
        </div>
    </div>
</nav>
<div class="container mt-1">
    <div class="row ">
        <div class="col-sm mt-5">
            Üdvözöljük Állatkertünkben!
        </div>
    </div>
    <div class="row mt-5">
        <div class="d-grid gap-2 col-6 mx-auto">
            <c:if test="${cookie.email == null}">
                <button class="btn btn-primary btn-lg" type="button" onclick="location.href ='pages/adopter-registration.jsp'">
                    Regisztráció
                </button>
                <button class="btn btn-primary btn-lg" type="button" onclick="location.href ='pages/login.jsp'">
                    Bejelentkezés
                </button>
            </c:if>
            <button class="btn btn-primary btn-lg" type="button" onclick="location.href ='pages/animal-list.jsp'">
                Állataink
            </button>
        </div>
    </div>
</div>
</body>