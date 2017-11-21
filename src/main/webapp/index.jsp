<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Index</title></head>
<body>
<section class="left-bar">
    <c:choose>
        <c:when test="${sessionScope.player.role != null && 'PLAYER'.equals(sessionScope.player.role.toString())}">
            <%@include file="user.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="login.jsp" %>
            <a href="register.jsp">Go to Registration!</a><br>
        </c:otherwise>
    </c:choose>
</section>

<div align="center">
    <h2>Welcome to start page of application!</h2>
</div>
</body>
</html>

