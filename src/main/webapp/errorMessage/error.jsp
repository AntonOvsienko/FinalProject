<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="http://com.ua/FinalProject" %>
<html>
<head>
    <title>Error 404</title>
</head>
<body>
<h1>
    <c:choose>
        <c:when test="${errorMessage ==1}">
            <loc:print key="DatabaseOutDate"/>
        </c:when>
        <c:when test="${errorMessage ==2}">
            <loc:print key="IncorrectLoginOrPassword"/>
        </c:when>
        <c:otherwise>
            <loc:print key="LostConnectionDatabase"/>
        </c:otherwise>
    </c:choose>

</h1>
</body>
</html>
