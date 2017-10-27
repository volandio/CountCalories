<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<form method="post" action="/meals">
    <label>Максимальное количество калорий в день = </label>
    <input type="number" name="maxSumCalories" value="${user.caloriesPerDay}"/>
    <button>Изменить</button>
</form>

<a href="${pageContext.servletContext.contextPath}/createMeal.jsp">Create Meal</a>
<table class="tg">
    <tbody>
    <tr>
        <th width="80">dateTime</th>
        <th width="120">description</th>
        <th width="120">calories</th>
        <%--<th width="120">exceed</th>--%>
    </tr>
    <c:forEach items="${list}" var="item">
    <tr>
        <c:choose>
            <c:when test="${item.exceed}">
                <td style="color:red"><c:out value="${item.dateTime}"/></td>
                <td style="color:red"><c:out value="${item.description}"/></td>
                <td style="color:red"><c:out value="${item.calories}"/></td>
            </c:when>
            <c:otherwise>
                <td><c:out value="${item.dateTime}"/></td>
                <td><c:out value="${item.description}"/></td>
                <td><c:out value="${item.calories}"/></td>
            </c:otherwise>
        </c:choose>
        <td><a href="${pageContext.servletContext.contextPath}/editMeal?id=${item.mealId}">Edit Meal</a></td>
        <td><a href="${pageContext.servletContext.contextPath}/deleteMeal?id=${item.mealId}">Delete Meal</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
