<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="../index.jsp">Zoo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" href="list-contact.jsp">Állataink</a>
                <c:if test="${cookie.email == null}">
                    <a class="nav-link" href="add-contact.jsp">Regisztráció</a>
                    <a class="nav-link" href="login.jsp">Bejelentkezés</a>
                </c:if>
                <c:if test="${cookie.email != null}">
                    <a class='nav-link' href='#'>${cookie.email.value}</a>
                    <a class='dropdown-item' href='../LogoutController'>Kijelentkezés</a>
                </c:if>
            </div>
        </div>
    </div>
</nav>