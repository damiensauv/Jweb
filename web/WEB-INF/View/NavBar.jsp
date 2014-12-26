<%--
  Created by IntelliJ IDEA.
  User: damien
  Date: 16/12/14
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
    <li><a href="<c:url value="/"/>">Home</a></li>
    <li><a href="<c:url value="/login"/>">Connexion</a></li>
    <li><a href="<c:url value="/register"/>">Register</a></li>
    <li><a href="<c:url value="/logout"/>">Logout</a></li>
    <li><a href="<c:url value="/adminProduct"/>">Admin - Ajout produit</a></li>


    <li><a href="<c:url value="/"/>">Admin - Gestion des Users</a></li>
    <li><a href="<c:url value="/"/>">gestion des stock</a></li>

    <c:if test="${!empty session_user}">
        <p>${session_user.get_pseudo()}</p>
    </c:if>


</ul>

