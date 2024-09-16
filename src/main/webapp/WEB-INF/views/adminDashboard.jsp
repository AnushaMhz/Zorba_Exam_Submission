<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
<h2> User Information</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    <c:forEach var="userInfo" items="${userInfo}">
        <tr>
            <td>${userInfo.id}</td>
            <td>${userInfo.name}</td>
            <td>${userInfo.email}</td>
            <td><a href="removeUser?id=${userInfo.id}">Remove</a></td>
        </tr>
    </c:forEach
    <a href="${pageContext.request.contextPath}/inventory">View Inventory Details</a>
</table>
</html>