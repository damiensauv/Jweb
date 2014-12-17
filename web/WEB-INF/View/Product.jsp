<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damien
  Date: 16/12/14
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>

<c:import url="NavBar.jsp"/>

    <h1>Product</h1>


    <fieldset>
        <p>-----Name------</p>
        <img src="http://www.francebleu.fr/sites/default/files/imagecache/462_ressource/2013/08/13/766274/images/4l-photo-web.jpg" height="100" width="100">
        <p>prod 1</p>
        <p>Description :  </p>
        <p>Stock :  </p>
        <p>Prix :  </p>


        <p>Quantite</p>
        <select>
            <c:forEach var="i" begin="1" end="10">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>

        <button>Valider</button>

    </fieldset>

</body>
</html>
