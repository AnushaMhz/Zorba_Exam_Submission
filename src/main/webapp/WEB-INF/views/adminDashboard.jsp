<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Excel File</title>
</head>
<body>
<h2>Customer Information</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    <c:forEach var="userInfos" items="${userInfos}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td><a href="removeUser?id=${user.id}">Remove</a></td>
        </tr>
    </c:forEach
    <a href="${pageContext.request.contextPath}/inventory">View Inventory Details</a>
</table>
</html>