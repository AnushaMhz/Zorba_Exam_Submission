<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<form action="getInventoryByCategory" method="get">
    <label>Select Category:</label>
    <select name="categoryId" onchange="this.form.submit()">
        <c:forEach var="category" items="${categories}">
            <option value="${category.categoryId}">${category.categoryName}</option>
        </c:forEach>
    </select>
</form>

<c:if test="${not empty inventoryItems}">
    <h2>Inventory Items:</h2>
    <c:forEach var="item" items="${inventoryItems}">
        <div>
            <p>Inventory Name: ${item.inventoryName}</p>
            <p>Quantity: ${item.inventoryQuantity}</p>
            <p>Price: ${item.inventoryPrice}</p>
            <p>Description: ${item.inventoryDescription}</p>
            <img src="${item.inventoryImage}" alt="Item Image" width="100" />
            <form action="addToCart" method="post">
                <input type="hidden" name="customerId" value="${customerId}" />
                <input type="hidden" name="inventoryId" value="${item.inventoryId}" />
                <input type="submit" value="Add to Cart" />
            </form>
        </div>
    </c:forEach>
</c:if>
