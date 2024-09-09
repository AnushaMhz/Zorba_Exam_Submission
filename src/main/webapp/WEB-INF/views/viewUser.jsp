<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<table>
    <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Username</th>
        <th>Roles</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><a href="addRoles/${user.userId}">${user.userId}</a></td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.mobile}</td>
            <td>${user.username}</td>
            <td>
                <c:forEach items="${user.roles}" var="role">
                    ${role.roleName}<c:if test="${!roleStatus.last}">, </c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>