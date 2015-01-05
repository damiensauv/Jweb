<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damien
  Date: 16/12/14
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<c:import url="NavBar.jsp"/>



<form method="post" action="adminProductAdd ">

    <label for="img">Image - url<span class="requis">*</span></label>
    <input type="url" id="img" name="img" value="" required/>

    <label for="name">Name<span class="requis">*</span></label>
    <input type="name" id="name" name="name" value="" required/>


    <label for="description">Description</label>
    <textarea name="description" id="description"></textarea>


    <label for="stock">Stock<span class="requis">*</span></label>
    <input type="number" id="stock" name="stock" value="" required/>

    <label for="prix">Prix<span class="requis">*</span></label>
    <input type="number" id="prix" name="prix" value="" required/>

    <br />
    <input type="submit" value="submit" />

</form>



</body>
</html>
