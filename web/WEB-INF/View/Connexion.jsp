<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damien
  Date: 16/12/14
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Connexion</title>
</head>
<body>

<c:import url="NavBar.jsp"/>

        <p>Page de connexion</p>

        <form method="post" action="login">
            <fieldset>
                <legend>Login</legend>
                <p>Vous pouvez vous loger ici</p>

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="" size="20" maxlength="60" required/>

                <br />

                <label for="password">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" required/>

                <br />
                <input type="submit" value="login"  />
                <br />
            </fieldset>
        </form>




</body>
</html>
