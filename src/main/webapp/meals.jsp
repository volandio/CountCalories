<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/createMeal.jsp">Create Meal</a>
<table class="tg">
    <tbody>
    <tr>
        <th width="80">dateTime</th>
        <th width="120">description</th>
        <th width="120">calories</th>
    </tr>
    <c:forEach items="${list}" var="item">
    <tr>
            <td><c:out value="${item.dateTime}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.calories}"/></td>
            <td><a href="${pageContext.servletContext.contextPath}/editMeal?id=${item.mealId}">Edit Meal</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/deleteMeal?id=${item.mealId}">Delete Meal</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
