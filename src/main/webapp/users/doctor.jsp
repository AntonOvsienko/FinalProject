<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h1>Hello Doctor</h1>
<c:>
    Login:${login}<br>
    Name:${name}<br>
    Surname:${surname}<br>
    Passport:${passport}<br>
</c:>

<form id="auth" action="/controller" method="get">
    <input name="address" value="users/updateAccount.jsp" hidden>
    <p><input type="submit" value="Обновить профиль"></p>
    <form id="auth1" action="controller" method="get">
        <input name="command" value="exit" hidden>
        <p><input type="submit" value="Закончить сессию"></p>
    </form>
    <form id="auth2" action="/controller" method="post">
        <input name="command" value="exit" hidden>
        <p><input type="submit" value="Закончить сессию"></p>
    </form>
</body>
</html>