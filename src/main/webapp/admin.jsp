<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Administrator</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<table class="tg">
    <tbody>
    <tr>
        <th width="80">userId</th>
        <th width="120">enabled</th>
        <th width="120">isAdmin</th>
        <th width="120">name</th>
        <th width="120">email</th>
        <th width="120">registered</th>
        <th width="120">caloriesPerDay</th>
    </tr>
    <c:forEach items="${users}" var="item">
    <tr>
        <td><c:out value="${item.userId}"/></td>
        <td><c:out value="${item.enabled}"/></td>
        <td><c:out value="${item.admin}"/></td>
        <td><c:out value="${item.name}"/></td>
        <td><c:out value="${item.email}"/></td>
        <td><c:out value="${item.registered}"/></td>
        <td><c:out value="${item.caloriesPerDay}"/></td>
        <td><a href="${pageContext.servletContext.contextPath}/administrator?id=${item.userId}">Delete User</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
