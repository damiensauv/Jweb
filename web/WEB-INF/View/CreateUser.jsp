<%--
  Created by IntelliJ IDEA.
  User: damien
  Date: 15/12/14
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>Inscription</title>
</head>

<body>

<c:import url="NavBar.jsp"/>

<form method="post" action="register">
    <fieldset>
        <legend>Inscription</legend>
        <p>Vous pouvez vous inscrire via ce formulaire.</p>


        <label for="email">Adresse email <span class="requis">*</span></label>
        <input type="email" id="email" name="email" value="" size="20" maxlength="60" required/>
        <span class="erreur">${error['email']}</span>
        <br />

        <label for="password">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="password" name="password" value="" size="20" maxlength="20" required/>
        <span class="erreur">${error['password']}</span>
        <br />

        <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
        <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" required/>
        <br />

        <label for="pseudo">Nom d'utilisateur</label><span class="requis">*</span>
        <input type="text" id="pseudo" name="pseudo" value="" size="20" maxlength="20" required/>
        <span class="erreur">${ error['pseudo']}</span>
        <br />

        <label for="news">Newsletter</label>
        <input type="checkbox" id="news" name="news" />

        <br />

        <input type="submit" value="register"  />
        <br />
    </fieldset>
</form>

<%-- JSTL --%>



<c:if test="${sucess == 'ok'}">
    <p>Felicitations votre compte est cree, vous pouvez vous connecter <a href="<c:url value="/login"/>">ici</a>  </p>
</c:if>
<c:if test="${sucess == 'ko'}">
    <p>${error['gen']}</p>
</c:if>





</body>
</html>