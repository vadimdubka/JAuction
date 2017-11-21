<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="index.jsp">Go back</a>
<table border="true">
    <tr>
        <td>Name</td>
        <td>Middle Name</td>
        <td>Last Name</td>
    </tr>
    <c:forEach var="player" items="${requestScope.players}">
        <jsp:useBean id="player" type="com.dubatovka.app.entity.Player" scope="page"/>
        <tr>
            <td>${player.profile.fName}</td>
            <td>${player.profile.mName}</td>
            <td>${player.profile.lName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
