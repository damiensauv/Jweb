<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Product All</title>
</head>
<body>

<c:import url="NavBar.jsp"/>

<h1>Product All</h1>

<c:forEach items="${productAll}" var="product" varStatus="status">
    <fieldset>
        <p> ----- <a href="<c:url value="/product?id=${product.get_id()}"/>">${product.get_name()}</a> ------ </p>

        <img src="<c:out value="${imageAll[status.index].get_url()}"/>" height="100" width="100">

        <p>Description :  <c:out value="${product.get_description()}" /></p>
        <p>Stock :  <c:out value="${product.get_stock()}" /></p>
        <p>Note : <c:out value="${product.get_average()}" /></p>
        <p>Prix :  <c:out value="${product.get_price()}" /></p>
    </fieldset>
</c:forEach>

</body>
</html>
