<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
    <li><a href="<c:url value="/"/>">Home</a></li>

<c:if test="${empty session_user}">
    <li><a href="<c:url value="/login"/>">Connexion</a></li>
    <li><a href="<c:url value="/register"/>">Register</a></li>

</c:if>

    <c:if test="${!empty session_user}">
        <li><a href="<c:url value="/productAll"/>">Liste produit</a></li>
        <li><a href="<c:url value="/profile"/>">Profile</a></li>

        <li><a href="<c:url value="/logout"/>">Logout</a></li>


        <!-- Voir comment faire pour l'enum-->
        <c:if test="${session_user.get_role() eq 'ADMIN'}">
            <li><a href="<c:url value="/adminProductAdd"/>">Admin - Ajout produit</a></li>
            <li><a href="<c:url value="/adminUser"/>">Admin - Gestion des Users</a></li>
        </c:if>

        <li>${session_user.get_pseudo()}</li>

    </c:if>

</ul>

