<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
    <h2>Login</h2>
  <form action="adminLogin" method="post">
      <label for="username">User Name:</label>
      <input type="text" id="username" name="username" required>

      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required>

      <label for="role">Role:</label>
      <input type="text" id="role" name="role" value="Admin" readonly>

      <input type="submit" value="Login">
  </form>
</body>
</html>