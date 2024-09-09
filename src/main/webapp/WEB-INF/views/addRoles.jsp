<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Add Role</title>
</head>
<body>
    <h2>Add Role</h2>

    <form action="assignRole" method="post">
        <input type="hidden" name="userId" value="${user.userId}"/>
        <label>Role:</label>
        <select name="roleName">
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
            <option value="VENDOR">VENDOR</option>
        </select>
        <input type="submit" value="Add Role"/>
    </form>
</body>
</html>