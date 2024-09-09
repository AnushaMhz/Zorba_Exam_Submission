<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h2>User Information Form</h2>

    <form:form modelAttribute="userInfo" action="saveUser" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Mobile:</td>
                <td><form:input path="mobile" /></td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save User" />
                </td>
            </tr>
        </table>
    </form:form>

    <c:if test="${!empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

</body>
</html>