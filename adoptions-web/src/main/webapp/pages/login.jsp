<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <jsp:include page="common/common-header.jsp"/>
    <title>Login</title>
</head>
<body>
<div class="container">
    <form action="../LoginController" method="post">
        <div class="form-group">
            <label for="email">Email cím</label>
            <input required name="email" type="email" class="form-control" id="email"
                   placeholder="Email"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input required name="password" type="password" class="form-control" id="password"
                   placeholder="Password"/>
        </div>
        <button id="submit" type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
