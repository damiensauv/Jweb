
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - User</title>
</head>
<body>
<c:import url="NavBar.jsp"/>

<h2>Admin User</h2>

<span class="erreur">${simple_error}</span>

<fieldset>
<form method="post" action="adminUser">

    <select name="user">
        <c:forEach items="${listUser}" var="usr" varStatus="status">
            <option name="${usr.get_email()}">${usr.get_email()}</option>
        </c:forEach>
    </select>

    <select name="action">
        <option name="upAdm">Up Admin</option>
        <option name="upUsr">Up Simple Utilisateur</option>
        <option name="delUsr">Supprimer Utilisateur</option>
    </select>

    <input type="submit" value="submit" />

</form>
</fieldset>


</body>
</html>
