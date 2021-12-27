<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="http://com.ua/FinalProject" %>
<html>
<head>
    <meta charset="utf-8">
    <title>start</title>
    <style>
        @import url(index_style.css);
    </style>
</head>
<body>
<ul id="nav">
    <c:forTokens items="${initParam['locales']}"
                 var="locale" delims=" ">
        <li class="left">
            <p><a href="/changeLocale.jsp?localeToSet=${locale}&pageToForward=index.jsp&basename=message_${locale}">
                <img src="${locale}.png" width="20" height="20"></a></p>
        </li>
    </c:forTokens>
</ul>
<div class="form">
    <div class="ribbon"></div>
    <div class="login">
        <h1><loc:print key="startPage_Hello" /></h1>
        <p class="welcome"><loc:print key="startPage_Insert_Login_Password"/></p>
        <form action="controller" method="post">
            <input name="command" value="login" hidden>
            <div class="input">
                <div class="blockinput">
                    <i class="icon-envelope-alt"></i><input name="globalLogin"
                                       placeholder="<loc:print key="startPage_Login_Placeholder"/>">
                </div>
                <div class="blockinput">
                    <i class="icon-unlock"></i><input name="password" type="password"
                                       placeholder="<loc:print key="startPage_Password_Placeholder"/>">
                </div>
            </div>
            <button type="submit"><loc:print key="startPage_Submit_Login"/></button>
        </form>
    </div>
</div>
</body>
</html>