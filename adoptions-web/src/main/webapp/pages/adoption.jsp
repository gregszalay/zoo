<%@ page import="hu.alkfejl.model.Phone" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="common/common-header.jsp"/>
    <script src="../js/adopter-registration.js"></script>
    <title>Örökbefogadás</title>
</head>
<body>
<jsp:include page="common/menu.jsp"/>
<jsp:include page="/AdoptionController"/>
<div class="container">
    <form action="../AdoptionController" method="post">
        <div class="form-group">
            <label for="animalName">Állat neve</label>
            <input name="animalName" type="text" class="form-control" id="animalName"
                   value="${requestScope.animalToBeAdopted.name}"/>
        </div>
        <div class="form-group">
            <label for="animalId">Állat azonosítója</label>
            <input name="animalId" type="text" class="form-control" id="animalId"
                   value="${requestScope.animalToBeAdopted.id}"/>
        </div>
        <div class="form-group">
            <img src="${requestScope.animalToBeAdopted.picture}" alt="${requestScope.animalToBeAdopted.name} fotója" />
        </div>
        <div class="form-group">
            <label for="supportType">Támogatás mennyisége</label>
            <select name="supportType" class="form-control" id="supportType">
                <c:forEach var="supportType" items="${requestScope.supportTypeList}">
                    <option value="${supportType}">${supportType}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="supportAmount">Támogatás mennyisége</label>
            <input name="supportAmount" type="text" class="form-control" id="supportAmount"
                   placeholder="Mennyiség számban"/>
        </div>
        <div class="form-group">
            <label for="supportFrequency">Támogatás gyakorisága</label>
            <select name="supportFrequency" class="form-control" id="supportFrequency">
                <c:forEach var="supportFrequency" items="${requestScope.supportFrequencyList}">
                    <option value="${supportFrequency}">${supportFrequency}</option>
                </c:forEach>
            </select>
        </div>
        <button id="submit" type="submit" class="btn btn-primary">Örökbefogadom!</button>
    </form>
</div>
</body>
</html>
