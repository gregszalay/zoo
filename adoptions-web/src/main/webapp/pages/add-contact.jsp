<%@ page import="hu.alkfejl.model.Phone" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <jsp:include page="common/common-header.jsp"/>
    <script src="../js/add-contact.js"></script>
    <title>Add Contact</title>
</head>
<body>
<jsp:include page="common/menu.jsp"/>
<div class="container">

<%--    <form action="../ContactController" method="post">--%>
    <form action="../RegistrationController" method="post" onSubmit="return checkPassword(this)">

        <div class="form-group">
            <label for="lastName">Vezetéknév</label>
            <input required name="lastName" type="text" class="form-control" id="lastName"
                   placeholder="Enter your last name"/>
        </div>

        <div class="form-group">
            <label for="firstName">Keresztnév</label>
            <input name="firstName" type="text" class="form-control" id="firstName"
                   placeholder="First Name"/>
        </div>

        <div class="form-group">
            <label for="email">Email cím</label>
            <input required name="email" type="email" class="form-control" id="email"
                   placeholder="Email"/>
        </div>

        <div class="form-group">
            <label for="password1">Választott Jelszó</label>
            <input name="password1" type="text" class="form-control" id="password1"
                   placeholder="Your password"/>
        </div>

        <div class="form-group">
            <label for="password2">Választott Jelszó ismét</label>
            <input name="password2" type="text" class="form-control" id="password2"
                   placeholder="Your password again"/>
        </div>


        <button id="submit" type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>


</html>