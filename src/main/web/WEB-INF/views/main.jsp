<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 29.03.2019
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Resume</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>
<body>
<div class="resumeTitle">Резюме</div>
<div class="bt1">
    <a align="center" href="employee" class="button-main">Employee</a>
    <a align="center" href="job" class="button-main">Job</a>
    <a align="center" href="address" class="button-main">Address</a>
</div>
<div class="details">
    <label class="txt"> Upload cv: </label><br>
    <form action="${pageContext.request.contextPath}/uploadFile" method="post" enctype="multipart/form-data">
        <center><input class="btn btn-success custom-width4" name="data" type="file" value="Chose file" accept=".json, .xml, .txt" ></center>
        <input class="btn btn-success custom-width" type="submit" value="Upload"> <br>
    </form>
</div>
</body>
</html>

