<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="common/common-header.jsp"/>
    <title>Állataink</title>
</head>
<body style="padding: 0">
<jsp:include page="common/menu.jsp"/>
<jsp:include page="/AnimalListController"/>
<nav class="navbar navbar-light bg-light">
    <form class="form-inline" action="../pages/animal-list.jsp" method="get">
        <input name="searchedTerm" class="form-control mr-sm-2" type="text" placeholder="Keresett kifejezés"
               aria-label="Keresés">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Keresés</button>
    </form>
</nav>
<div class="container">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Név</th>
            <th scope="col">Faj</th>
            <th scope="col">Bemutatkozás</th>
            <th scope="col">Születési év</th>
            <th scope="col">Fotó</th>
            <c:if test="${cookie.email != null}">
                <td></td>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="animal" items="${requestScope.animalList}">
            <tr>
                <td>${animal.name}</td>
                <td>${animal.species}</td>
                <td>${animal.introduction}</td>
                <td>${animal.birthYear}</td>
                <td><img src="${animal.picture}" alt="${animal.name} fotója"/></td>
                <c:if test="${cookie.email != null}">
                    <td>
                        <button class="btn btn-outline-success my-2 my-sm-0"
                                onclick="location.href ='adoption.jsp?animalId=${animal.id}'">Örökbefogadom!
                        </button>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

