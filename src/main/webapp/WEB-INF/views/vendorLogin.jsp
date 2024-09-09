<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOC TYPE html>
<html>
<head>
    <title>Vendor Login</title>
</head>
<body>
    <h2>Vendor Login</h2>
    <form action="${pageContext.request.contextPath}/vendor/login" method="post">
        <label>Username:</label>
        <input type="text" name="username" required/><br/>

        <label>Password:</label>
        <input type="password" name="password" required/><br/>

        <label>Role:</label>
        <input type="text" value="Vendor" readonly/><br/>

        <input type="submit" value="Login"/>
    </form>
</body>
</html>