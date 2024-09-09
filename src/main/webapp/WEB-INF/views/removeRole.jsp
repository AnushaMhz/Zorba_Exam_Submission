<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html>
<head>
    <title>Remove Roles</title>
</head>
<body>
    <h2>Remove Roles from User</h2>
    <form action="${pageContext.request.contextPath}/role/delete" method="post">
        <input type="hidden" name="userId" value="${user.userId}"/>
        <c:forEach var="role" items="${roles}">
            <input type="checkbox" name="roleIds" value="${role.roleId}"/> ${role.roleName}<br/>
        </c:forEach>
        <input type="submit" value="Remove Selected Roles"/>
    </form>
</body>
</html>