<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<html>
<head>
    <jsp:include page="common/common-header.jsp"/>
    <title>List Contacts</title>
</head>

<body>
<jsp:include page="common/menu.jsp"/>
<jsp:include page="/AnimalListController"/>
<nav class="navbar navbar-light bg-light">

    <form class="form-inline" action="../pages/list-contact.jsp" method="get">
        <input name="searchedTerm" class="form-control mr-sm-2" type="text"  placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>

</nav>
<div class="container">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">name</th>
            <th scope="col">species</th>
            <th scope="col">introduction</th>
            <th scope="col">birthYear</th>
            <th scope="col">picture</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.animalList}">
            <tr>
                <td>${item.name}</td>
                <td>${item.species}</td>
                <td>${item.introduction}</td>
                <td>${item.birthYear}</td>
                <td>
                    <img src="${item.picture}" alt="${item.name} fotója" />
                </td>
                <td>
                    <form class="form-inline" action="../pages/list-contact.jsp" method="get"

                    >
                        <input name="searchedTerm" class="form-control mr-sm-2" type="text"
                               placeholder="Search" aria-label="Search"
                               animalId="${item.id}"
                               photo="${item.picture}"
                        >
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>


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
                </td>




            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>

