<%@ page import="hu.alkfejl.model.Phone" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="common/common-header.jsp"/>
    <script src="../js/add-contact.js"></script>
    <title>Regisztráció</title>
</head>
<body>
<jsp:include page="common/menu.jsp"/>
<jsp:include page="/RegistrationController"/>
<div class="container">
    <form action="../RegistrationController" method="post">
        <div class="form-group">
            <label for="lastName">Vezetéknév</label>
            <input required name="lastName" type="text" class="form-control" id="lastName"
                   placeholder="Kérjük, adja meg a vezetéknevét!"/>
        </div>
        <div class="form-group">
            <label for="firstName">Keresztnév</label>
            <input name="firstName" type="text" class="form-control" id="firstName"
                   placeholder="Kérjük, adja meg a keresztnevét!"/>
        </div>
        <div class="form-group">
            <label for="email">Email cím</label>
            <input required name="email" type="email" class="form-control" id="email"/>
        </div>
        <div class="form-group">
            <label for="password1">Választott Jelszó</label>
            <input name="password1" type="password" class="form-control" id="password1"/>
        </div>
        <button id="submit" type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>