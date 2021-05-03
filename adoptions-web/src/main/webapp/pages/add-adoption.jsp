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
    <form action="../AdoptionController" method="post" >

        <div class="form-group">
            <label for="supportType">Támogatás típusa</label>
            <input name="supportType" type="text" class="form-control" id="supportType"
                   placeholder="Milyen jellegű támogatást szeretne nyújtani az állatnak?"/>
        </div>

        <div class="form-group">
            <label for="supportAmount">Támogatás mennyisége</label>
            <input name="supportAmount" type="text" class="form-control" id="supportAmount"
                   placeholder="Mennyiség számban"/>
        </div>


        <button id="submit" type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>


</html>