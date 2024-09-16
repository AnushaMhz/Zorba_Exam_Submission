<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Excel File</title>
</head>
<body>
<c:url value= "/uploadFile" >
<form action="uploadFile" method="post" enctype ="multipart/form-data">
  <input type="hidden" name="inventoryId" id="inventoryId" value="${inventoryId}"/>
  <label for="upload">Upload the Excel </label>
  <input name="upload" id="upload" type="file" accept= "inventory.xlsx" required />
  <button type="submit">Upload</button>
</form>
</body>
</html>