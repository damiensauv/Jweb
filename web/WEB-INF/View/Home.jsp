<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damien
  Date: 15/12/14
  Time: 01:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

    <c:import url="NavBar.jsp"></c:import>

    <h2>Home</h2>

    <span class="error">${simple_error}</span>

    <p>Bienvenue sur le Site</p>

</body>
</html>
