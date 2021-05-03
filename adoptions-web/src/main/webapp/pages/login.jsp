<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="common/common-header.jsp"/>
    <title>Login</title>
</head>
<body>
<jsp:include page="common/menu.jsp"/>
<div class="container">
    <form action="../LoginController" method="post">
        <div class="form-group">
            <label for="email">Email cím</label>
            <input required name="email" type="email" class="form-control" id="email"/>
        </div>
        <div class="form-group">
            <label for="password">Jelszó</label>
            <input required name="password" type="password" class="form-control" id="password"/>
        </div>
        <button id="submit" type="submit" class="btn btn-primary">Belépés</button>
    </form>
</div>
</body>
</html>
