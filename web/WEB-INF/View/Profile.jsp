<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damien
  Date: 15/12/14
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<c:import url="NavBar.jsp"/>

   <h2>Profile</h2>

<p>Pseudo : ${user_co.get_pseudo()}</p>
<p>Email : ${user_co.get_email ()}</p>


</body>
</html>
