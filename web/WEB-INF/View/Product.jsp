<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Product</title>
</head>
<body>

<c:import url="NavBar.jsp"/>

    <h1>Product</h1>

<fieldset>
    <p> ----- <a href="<c:url value="/product?id=${product.get_id()}"/>">${product.get_name()}</a> ------ </p>
    <img src="<c:out value="${image.get_url()}"/>" height="100" width="100">
    <p>Description :  <c:out value="${product.get_description()}" /></p>
    <p>Stock :  <c:out value="${product.get_stock()}" /></p>
    <p>Prix :  <c:out value="${product.get_price()}" /></p>
    <p>Note moyenne : <c:out value="${product.get_average()}" /></p>
    <p>Quantite</p>
    <select>
        <c:forEach var="i" begin="1" end="10">
            <option value="${i}">${i}</option>
        </c:forEach>
    </select>
    <button>Valider</button>
</fieldset>

<p>Commentaire</p>
<form method="post" action="product">
    <textarea id="coms" name="coms"></textarea>

    <input type="radio" name="star" value="1">1</input>
    <input type="radio" name="star" value="2">2</input>
    <input type="radio" name="star" value="3">3</input>
    <input type="radio" name="star" value="4">4</input>
    <input type="radio" name="star" value="5">5</input>

    <input type="submit" value="submit"/>
</form>

<c:forEach items="${listComment}" var="comment" varStatus="status">
    <fieldset>
        <p><c:out value="${ (db.get_specific_user(comment.get_userId())).get_pseudo()}"></c:out></p>
        <p>Note:<c:out value="${comment.get_stars()}">${comment.get_stars()}</c:out> / 5</p>
        <p><c:out value="${comment.get_comment()}">${comment.get_comment()}</c:out></p>
    </fieldset>
</c:forEach>

</body>
</html>
