<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html>
<head>
    <title>Add Inventory</title>
</head>
<body>
    <h2>Add Inventory</h2>
    <form action="${pageContext.request.contextPath}/inventory/save" method="post" enctype="multipart/form-data">
        <label>Category:</label>
        <input type="text" name="categoryName" required/><br/>

        <label>Inventory Name:</label>
        <input type="text" name="inventoryName" required/><br/>

        <label>Quantity:</label>
        <input type="number" name="quantity" required/><br/>

        <label>Price per Unit:</label>
        <input type="number" step="0.01" name="price" required/><br/>

        <label>Image:</label>
        <input type="file" name="image" accept="image/*" required/><br/>

        <label>Description:</label>
        <textarea name="description" required></textarea><br/>

        <input type="submit" value="Add Inventory"/>
    </form>
</body>
</html>